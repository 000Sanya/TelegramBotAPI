package com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor

import com.github.insanusmokrassar.TelegramBotAPI.bot.BaseRequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.base.MultipartRequestCallFactory
import com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.base.SimpleRequestCallFactory
import com.github.insanusmokrassar.TelegramBotAPI.bot.exceptions.newRequestException
import com.github.insanusmokrassar.TelegramBotAPI.bot.settings.limiters.EmptyLimiter
import com.github.insanusmokrassar.TelegramBotAPI.bot.settings.limiters.RequestLimiter
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import com.github.insanusmokrassar.TelegramBotAPI.types.Response
import com.github.insanusmokrassar.TelegramBotAPI.types.RetryAfterError
import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.engine.HttpClientEngine
import kotlinx.coroutines.delay
import kotlinx.coroutines.io.readUTF8Line
import kotlinx.io.charsets.Charset
import kotlinx.serialization.json.JSON

class KtorRequestsExecutor(
    token: String,
    private val client: HttpClient = HttpClient(),
    hostUrl: String = "https://api.telegram.org",
    callsFactories: List<KtorCallFactory> = emptyList(),
    excludeDefaultFactories: Boolean = false,
    private val requestsLimiter: RequestLimiter = EmptyLimiter,
    private val jsonFormatter: JSON = JSON.nonstrict
) : BaseRequestsExecutor(token, hostUrl) {
    constructor(
        token: String,
        engine: HttpClientEngine,
        hostUrl: String = "https://api.telegram.org"
    ) : this(
        token,
        HttpClient(engine),
        hostUrl
    )

    private val callsFactories: List<KtorCallFactory> = callsFactories.run {
        if (!excludeDefaultFactories) {
            asSequence().plus(SimpleRequestCallFactory()).plus(MultipartRequestCallFactory()).toList()
        } else {
            this
        }
    }

    override suspend fun <T : Any> execute(request: Request<T>): T {
        return requestsLimiter.limit {
            var call: HttpClientCall? = null
            for (factory in callsFactories) {
                call = factory.prepareCall(
                    client,
                    baseUrl,
                    request
                )
                if (call != null) {
                    break
                }
            }
            if (call == null) {
                throw IllegalArgumentException("Can't execute request: $request")
            }
            val content = StringBuilder().run {
                var read = call.response.content.readUTF8Line()
                while (read != null) {
                    append("$read\n")
                    read = call.response.content.readUTF8Line()
                }
            }.toString()
            val responseObject = jsonFormatter.parse(
                Response.serializer(request.resultSerializer()),
                content
            )
            responseObject.result ?: responseObject.parameters ?.let {
                val error = it.error
                if (error is RetryAfterError) {
                    delay(error.leftToRetry)
                    execute(request)
                } else {
                    null
                }
            } ?: call.let {
                throw newRequestException(
                    responseObject,
                    "Can't get result object"
                )
            }
        }
    }
}
package com.github.insanusmokrassar.TelegramBotAPI.bot.settings.limiters

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.util.concurrent.Executors
import kotlin.coroutines.*

private sealed class RequestEvent
private class AddRequest(
    val continuation: Continuation<Long>
) : RequestEvent()
private object CompleteRequest : RequestEvent()

@Serializable
data class PowLimiter(
    private val minAwaitTime: Long = 0L,
    private val maxAwaitTime: Long = 10000L,
    private val powValue: Double = 4.0,
    private val powK: Double = 0.0016
) : RequestLimiter {
    @Transient
    private val scope = CoroutineScope(
        Executors.newFixedThreadPool(3).asCoroutineDispatcher()
    )
    @Transient
    private val eventsChannel = Channel<RequestEvent>(Channel.UNLIMITED)
    @Transient
    private val awaitTimeRange = minAwaitTime .. maxAwaitTime

    init {
        scope.launch {
            var requestsInWork: Double = 0.0
            for (event in eventsChannel) {
                when (event) {
                    is AddRequest -> {
                        val awaitTime = ((Math.pow(requestsInWork, powValue) * powK) * 1000L).toLong()
                        requestsInWork++

                        event.continuation.resume(
                            if (awaitTime in awaitTimeRange) {
                                awaitTime
                            } else {
                                if (awaitTime < minAwaitTime) {
                                    minAwaitTime
                                } else {
                                    maxAwaitTime
                                }
                            }
                        )
                    }
                    is CompleteRequest -> requestsInWork--
                }
            }
        }
    }

    override suspend fun <T> limit(
        block: suspend () -> T
    ): T {
        val delayMillis = suspendCoroutine<Long> {
            scope.launch { eventsChannel.send(AddRequest(it)) }
        }
        delay(delayMillis)
        return try {
            block()
        } finally {
            eventsChannel.send(CompleteRequest)
        }
    }
}

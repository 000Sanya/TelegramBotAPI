package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.modify

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.ChatRequest
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.utils.toJson
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer
import kotlinx.serialization.json.JsonObject

@Serializable
data class SetChatPhoto (
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    val photo: MultipartFile = throw IllegalArgumentException("Unfortunately, this type of objects can't be parsed automatically")
): ChatRequest, MultipartRequest<Boolean> {
    override fun method(): String = "setChatPhoto"
    override fun resultSerializer(): KSerializer<Boolean> = BooleanSerializer
    override val mediaMap: Map<String, MultipartFile> = mapOf(photoField to photo)
    override val paramsJson: JsonObject = toJson(serializer())
}

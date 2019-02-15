package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InputMessageContent

import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.abstracts.InputMessageContent
import kotlinx.serialization.*

@Serializer(InputMessageContent::class)
object InputMessageContentSerializer : KSerializer<InputMessageContent> {
    override fun serialize(encoder: Encoder, obj: InputMessageContent) {
        when (obj) {
            is InputContactMessageContent -> InputContactMessageContent.serializer().serialize(encoder, obj)
            is InputLocationMessageContent -> InputLocationMessageContent.serializer().serialize(encoder, obj)
            is InputTextMessageContent -> InputTextMessageContent.serializer().serialize(encoder, obj)
            is InputVenueMessageContent -> InputVenueMessageContent.serializer().serialize(encoder, obj)
            else -> throw IllegalArgumentException("Unknown for serializing InputContactMessageContent")
        }
    }

    override fun deserialize(decoder: Decoder): InputMessageContent = throw IllegalStateException("Object can't be deserialized")
}
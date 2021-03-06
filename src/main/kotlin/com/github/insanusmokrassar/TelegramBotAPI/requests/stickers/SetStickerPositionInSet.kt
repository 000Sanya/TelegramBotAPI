package com.github.insanusmokrassar.TelegramBotAPI.requests.stickers

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.positionField
import com.github.insanusmokrassar.TelegramBotAPI.types.stickerField
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer

@Serializable
data class SetStickerPositionInSet(
    @SerialName(stickerField)
    val sticker: FileId,
    @SerialName(positionField)
    val position: Int
) : SimpleRequest<Boolean> {
    init {
        if (position < 0) {
            throw IllegalArgumentException("Position must be positive or 0")
        }
    }

    override fun method(): String = "setStickerPositionInSet"
    override fun resultSerializer(): KSerializer<Boolean> = BooleanSerializer
}

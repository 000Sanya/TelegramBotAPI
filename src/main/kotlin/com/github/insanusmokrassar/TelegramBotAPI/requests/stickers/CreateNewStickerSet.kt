package com.github.insanusmokrassar.TelegramBotAPI.requests.stickers

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.common.CommonMultipartFileRequest
import com.github.insanusmokrassar.TelegramBotAPI.requests.stickers.abstracts.StickerSetAction
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.stickers.MaskPosition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

fun CreateNewStickerSet(
    userId: UserId,
    name: String,
    sticker: InputFile,
    emojis: String,
    containsMasks: Boolean? = null,
    maskPosition: MaskPosition? = null
): Request<Boolean> {
    val data = CreateNewStickerSet(userId, name, emojis, sticker as? FileId, containsMasks, maskPosition)
    return when (sticker) {
        is MultipartFile -> CommonMultipartFileRequest(
            data,
            mapOf(pngStickerField to sticker)
        )
        is FileId -> data
    }
}

@Serializable
data class CreateNewStickerSet internal constructor(
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(nameField)
    override val name: String,
    @SerialName(emojisField)
    override val emojis: String,
    @SerialName(pngStickerField)
    val sticker: FileId? = null,
    @SerialName(containsMasksField)
    val containsMasks: Boolean? = null,
    @SerialName(maskPositionField)
    override val maskPosition: MaskPosition? = null
) : StickerSetAction {
    init {
        if(emojis.isEmpty()) {
            throw IllegalArgumentException("Emojis must not be empty")
        }
    }

    override fun method(): String = "createNewStickerSet"
}

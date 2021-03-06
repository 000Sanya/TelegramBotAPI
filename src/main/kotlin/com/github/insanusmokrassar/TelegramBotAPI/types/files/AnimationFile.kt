package com.github.insanusmokrassar.TelegramBotAPI.types.files

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimationFile(
    @SerialName(fileIdField)
    override val fileId: FileId,
    override val width: Int,
    override val height: Int,
    override val duration: Long? = null,
    override val thumb: PhotoSize? = null,
    @SerialName(fileNameField)
    override val fileName: String? = null,
    @SerialName(mimeTypeField)
    override val mimeType: String? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null
) : TelegramMediaFile, MimedMediaFile, ThumbedMediaFile, PlayableMediaFile, CustomNamedMediaFile, SizedMediaFile

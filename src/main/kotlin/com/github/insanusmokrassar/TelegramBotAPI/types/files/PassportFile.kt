package com.github.insanusmokrassar.TelegramBotAPI.types.files

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.TelegramMediaFile
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Important notice about this type of files: files by id are encrypted
 * https://core.telegram.org/bots/api#passportfile
 */
@Serializable
data class PassportFile (
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileSizeField)
    override val fileSize: Long,
    @SerialName(fileDateField)
    val fileDate: TelegramDate
) : TelegramMediaFile

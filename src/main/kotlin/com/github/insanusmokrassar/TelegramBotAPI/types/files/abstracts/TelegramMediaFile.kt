package com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId

/**
 * Declare common part of media files in Telegram. Note: it is not representation of `File` type
 */
interface TelegramMediaFile {
    val fileId: FileId
    val fileSize: Long?
}

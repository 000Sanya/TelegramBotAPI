package com.github.insanusmokrassar.TelegramBotAPI.types.message.content.media

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.SendDocument
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.MessageEntity
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.MarkdownParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.files.DocumentFile
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.CaptionedMediaContent
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MediaContent
import com.github.insanusmokrassar.TelegramBotAPI.utils.toMarkdownCaptions

data class DocumentContent(
    override val media: DocumentFile,
    override val caption: String? = null,
    override val captionEntities: List<MessageEntity> = emptyList()
) : MediaContent<DocumentFile>, CaptionedMediaContent {
    override fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        replyToMessageId: MessageIdentifier?,
        replyMarkup: KeyboardMarkup?
    ): Request<RawMessage> = SendDocument(
        chatId,
        media.fileId,
        media.thumb ?.fileId,
        toMarkdownCaptions().firstOrNull(),
        MarkdownParseMode,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )
}
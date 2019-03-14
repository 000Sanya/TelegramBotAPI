package com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates

import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.MediaGroupMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.update.EditMessageUpdate
import com.github.insanusmokrassar.TelegramBotAPI.types.update.MessageUpdate

data class EditMessageMediaGroupUpdate(
    override val updateId: UpdateIdentifier,
    override val data: MediaGroupMessage
) : MediaGroupUpdate {
    constructor(sourceUpdate: EditMessageUpdate) : this(
        sourceUpdate.updateId,
        sourceUpdate.data as MediaGroupMessage
    )
}
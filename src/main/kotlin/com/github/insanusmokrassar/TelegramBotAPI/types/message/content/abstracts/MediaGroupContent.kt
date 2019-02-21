package com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia.MediaGroupMemberInputMedia

interface MediaGroupContent : MessageContent {
    fun toMediaGroupMemberInputMedia(): MediaGroupMemberInputMedia
}
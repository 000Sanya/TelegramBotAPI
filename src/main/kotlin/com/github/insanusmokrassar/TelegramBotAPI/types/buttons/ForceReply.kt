package com.github.insanusmokrassar.TelegramBotAPI.types.buttons

import kotlinx.serialization.*

@Serializable
data class ForceReply(
    @Optional
    val selective: Boolean? = null
) : KeyboardMarkup {
    @SerialName("force_reply")
    val forceReply: Boolean = true
}
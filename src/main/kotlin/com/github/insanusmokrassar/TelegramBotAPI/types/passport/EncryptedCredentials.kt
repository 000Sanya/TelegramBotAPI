package com.github.insanusmokrassar.TelegramBotAPI.types.passport

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EncryptedCredentials (
    @SerialName(dataField)
    val data: TelegramEncryptedData,
    @SerialName(hashField)
    val hash: String,
    @SerialName(secretField)
    val secret: TelegramEncryptedData
)

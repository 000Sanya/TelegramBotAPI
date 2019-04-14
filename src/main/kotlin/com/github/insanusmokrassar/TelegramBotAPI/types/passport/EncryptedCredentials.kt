package com.github.insanusmokrassar.TelegramBotAPI.types.passport

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.utils.decodeBase64
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class EncryptedCredentials (
    @SerialName(dataField)
    val sourceData: TelegramEncryptedData,
    @SerialName(hashField)
    val sourceHash: String,
    @SerialName(secretField)
    val sourceSecret: TelegramEncryptedData
) {
    @Transient
    val encryptedData: String = sourceData.decodeBase64()

    @Transient
    val hash: String = sourceHash.decodeBase64()

    @Transient
    val encryptedSecret: String = sourceSecret.decodeBase64()
}

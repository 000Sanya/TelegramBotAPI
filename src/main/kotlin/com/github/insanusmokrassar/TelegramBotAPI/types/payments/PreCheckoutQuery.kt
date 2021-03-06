package com.github.insanusmokrassar.TelegramBotAPI.types.payments

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.payments.abstracts.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

// TODO:: separate to normal classes hierarchy
@Serializable
data class PreCheckoutQuery(
    @SerialName(idField)
    val id: PreCheckoutQueryId,
    @SerialName(fromField)
    val user: User,
    @Serializable(CurrencySerializer::class)
    @SerialName(currencyField)
    override val currency: Currency,
    @SerialName(totalAmountField)
    override val amount: Long,
    @SerialName(invoicePayloadField)
    val invoicePayload: InvoicePayload,
    @SerialName(shippingOptionIdField)
    val shippingOptionId: ShippingOptionIdentifier? = null,
    @SerialName(orderInfoField)
    val orderInfo: OrderInfo? = null
) : Currencied, Amounted

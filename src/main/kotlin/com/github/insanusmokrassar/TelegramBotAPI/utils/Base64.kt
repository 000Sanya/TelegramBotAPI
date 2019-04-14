package com.github.insanusmokrassar.TelegramBotAPI.utils

import java.nio.charset.Charset
import java.util.*

fun String.decodeBase64(): String = Base64.getDecoder().decode(this).toString(Charset.forName("UTF-8"))

package com.pirksni.leantech.extensions

import java.text.SimpleDateFormat
import java.util.Locale

private const val FORMAT_DATE_TIME = "dd.MM.yyyy"

fun Long.formatDate(formatting: String = FORMAT_DATE_TIME): String =
    SimpleDateFormat(formatting, Locale.getDefault()).format(this)

package com.example.weathertest.ui.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date?.toStringFormatted(format: String = "MMM dd, HH:mm", locale: Locale = Locale.ENGLISH): String {
    this?.let {
        return SimpleDateFormat(format, locale).format(
            this
        )
    } ?: return ""
}
package com.github.projektmagma.magmaapp.home.domain.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toUiDate(): String {
    val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT)
    val date = Date(this)

    return formatter.format(date)
}

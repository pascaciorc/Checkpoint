package com.pascaciorc.checkpoint.utils

import java.text.SimpleDateFormat
import java.util.*

fun Long.toDate(): String {
    val date = Date(this)
    val format = SimpleDateFormat("dd/MM/yyy")
    return format.format(date)
}
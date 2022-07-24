package com.team23.home.data.extensions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val ISO_8601_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
const val READABLE_DATE_TIME_FORMAT = "dd MMM yyyy HH:mm:ss"

fun String.toLocalDateTime(format: String): LocalDateTime =
    LocalDateTime.parse(this, DateTimeFormatter.ofPattern(format))

fun LocalDateTime.toString(format: String): String =
    this.format(DateTimeFormatter.ofPattern(format))

fun String.fromISO8601toReadableDateTime() =
    this.toLocalDateTime(ISO_8601_FORMAT).toString(READABLE_DATE_TIME_FORMAT)
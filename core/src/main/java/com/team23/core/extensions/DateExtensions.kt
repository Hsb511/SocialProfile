package com.team23.core.extensions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val ISO_8601_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
const val READABLE_DATE_TIME_FORMAT = "dd MMM yyyy HH:mm:ss"
const val BIRTHDATE_FORMAT = "dd/MM/yyyy"

fun String.toLocalDateTime(format: String): LocalDateTime =
    LocalDateTime.parse(this, DateTimeFormatter.ofPattern(format))

fun LocalDateTime.toString(format: String): String =
    this.format(DateTimeFormatter.ofPattern(format))

fun String.fromISO8601() = this.toLocalDateTime(ISO_8601_FORMAT)
fun String.fromISO8601toReadableDateTime() = this.fromISO8601().toString(READABLE_DATE_TIME_FORMAT)

fun LocalDateTime.toBirthDate() = this.toString(BIRTHDATE_FORMAT)

// TODO
fun LocalDateTime.toDuration() = "13 j"

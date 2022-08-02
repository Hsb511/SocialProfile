package com.team23.core.extensions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

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

fun LocalDateTime.toDuration(): String {
    val yearDiff = ChronoUnit.YEARS.between(this, LocalDateTime.now())
    if (yearDiff != 0L) {
        return "$yearDiff a"
    }
    val monthsDiff = ChronoUnit.MONTHS.between(this, LocalDateTime.now())
    if (monthsDiff != 0L) {
        return "$monthsDiff m"
    }
    val dayDiff = ChronoUnit.DAYS.between(this, LocalDateTime.now())
    if (dayDiff != 0L) {
        return "$dayDiff j"
    }
    val hourDiff = ChronoUnit.HOURS.between(this, LocalDateTime.now())
    if (hourDiff != 0L) {
        return "$hourDiff h"
    }
    val minuteDiff = ChronoUnit.MINUTES.between(this, LocalDateTime.now())
    if (minuteDiff != 0L) {
        return "$minuteDiff min"
    }
    return "${ChronoUnit.SECONDS.between(this, LocalDateTime.now())} s"
}


package com.team23.core

import com.team23.core.extensions.fromISO8601toReadableDateTime
import com.team23.core.extensions.toLocalDateTime
import com.team23.core.extensions.toString
import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDateTime
import java.time.Month
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class DateExtensionsTest {
    @Test
    fun `given invalid string date, when toLocalDateTime is called then throws DateTimeFormatException`() {
        // GIVEN
        val invalidStringDate = ""
        val validFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

        // THEN
        assertThrows(DateTimeParseException::class.java) {
            // WHEN
            invalidStringDate.toLocalDateTime(validFormat)
        }
    }

    @Test
    fun `given invalid date format, when toLocalDateTime is called then throws DateTimeFormatException`() {
        // GIVEN
        val validStringDate = "2020-05-24T14:53:17.598Z"
        val invalidFormat = ""

        // THEN
        assertThrows(DateTimeParseException::class.java) {
            // WHEN
            validStringDate.toLocalDateTime(invalidFormat)
        }
    }

    @Test
    fun `given valid string date and format, when toLocalDateTime is called, then a correct LocalDateTime is parsed`() {
        // GIVEN
        val stringDate = "2020-05-24T14:53:17.598Z"
        val format = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

        // WHEN
        val localDateTime = stringDate.toLocalDateTime(format)

        // THEN
        assertEquals(2020, localDateTime.year)
        assertEquals(Month.MAY, localDateTime.month)
        assertEquals(24, localDateTime.dayOfMonth)
        assertEquals(14, localDateTime.hour)
        assertEquals(53, localDateTime.minute)
        assertEquals(17, localDateTime.second)
    }

    @Test
    fun `given empty format, when toString is called, then returns empty string`() {
        // GIVEN
        val validDate = LocalDateTime.now()
        val invalidFormat = ""

        // WHEN
        val dateString = validDate.toString(invalidFormat)

        // THEN
        assertTrue(dateString.isEmpty())
    }

    @Test
    fun `given valid date and format, when toString is called, then a correct string date is parsed`() {
        // GIVEN
        val validDate = LocalDateTime.parse(
            "2020-05-24T14:53:17.598Z",
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        )
        val validFormat = "dd MMM yyyy HH:mm:ss"

        // WHEN
        val stringDate = validDate.toString(validFormat)

        // THEN
        assertEquals("24 mai 2020 14:53:17", stringDate)
    }

    @Test
    fun `given invalid date, when fromISO8601toReadableDateTime is called, then throws DateTimeFormatException`() {
        // GIVEN
        val invalidStringDate = ""

        // THEN
        val date = invalidStringDate.fromISO8601toReadableDateTime()

        // WHEN
        assertEquals("", date)
    }

    @Test
    fun `given valid date, when fromISO8601toReadableDateTime is called, then correct string date`() {
        // GIVEN
        val validStringDate = "2020-05-24T14:53:17.598Z"

        // WHEN
        val correctOutput = validStringDate.fromISO8601toReadableDateTime()

        // THEN
        assertEquals("24 mai 2020 14:53:17", correctOutput)
    }
}

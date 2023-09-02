package com.github.kontvip.horoscope.domain.model

import org.junit.Assert.*
import org.junit.Test

internal class DayTest {

    @Test
    fun `WithId day method throws exception if id is not 0, 1 or 2`() {
        assertEquals(Day.WithId(0).day(), Day.Yesterday())
        assertEquals(Day.WithId(1).day(), Day.Today())
        assertEquals(Day.WithId(2).day(), Day.Tomorrow())

        assertThrows(IllegalStateException::class.java) {
            Day.WithId(-1).day()
        }

        assertThrows(IllegalStateException::class.java) {
            Day.WithId(4).day()
        }

        assertThrows(IllegalStateException::class.java) {
            Day.WithId(5).day()
        }
    }

    @Test
    fun `test day yesterday`() {
        val yesterday = Day.Yesterday()

        assertEquals(yesterday.day(), "yesterday")
        assertTrue(yesterday.isSameDay(Day.Yesterday()))

        assertFalse(yesterday.isSameDay(Day.Today()))
        assertFalse(yesterday.isSameDay(Day.Tomorrow()))
    }

    @Test
    fun `test day today`() {
        val today = Day.Today()

        assertEquals(today.day(), "today")
        assertTrue(today.isSameDay(Day.Today()))

        assertFalse(today.isSameDay(Day.Yesterday()))
        assertFalse(today.isSameDay(Day.Tomorrow()))
    }

    @Test
    fun `test day tomorrow`() {
        val tomorrow = Day.Tomorrow()

        assertEquals(tomorrow.day(), "tomorrow")
        assertTrue(tomorrow.isSameDay(Day.Tomorrow()))

        assertFalse(tomorrow.isSameDay(Day.Today()))
        assertFalse(tomorrow.isSameDay(Day.Yesterday()))
    }
}
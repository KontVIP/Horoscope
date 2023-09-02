package com.github.kontvip.horoscope.presentation.core

import com.github.kontvip.horoscope.presentation.model.UiState
import org.junit.Assert.*
import org.junit.Test

class HoroscopeMapperTest {

    @Test
    fun `map horoscope data to ui state success`() {
        val horoscopeMapper = HoroscopeMapper()

        val expected = UiState.Success("test", "test", "test", "test", "test", "test", "test")
        val actual = horoscopeMapper.map("test", "test", "test", "test", "test", "test", "test")

        assertEquals(expected, actual)
    }

}
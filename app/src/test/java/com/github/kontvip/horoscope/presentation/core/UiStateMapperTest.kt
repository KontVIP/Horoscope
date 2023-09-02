package com.github.kontvip.horoscope.presentation.core

import com.github.kontvip.horoscope.data.model.Horoscope
import com.github.kontvip.horoscope.presentation.model.UiState
import org.junit.Assert.*
import org.junit.Test

class UiStateMapperTest {

    @Test
    fun `map horoscope to ui state success`() {
        val uiStateMapper = UiStateMapper(HoroscopeMapper())

        val emptyErrorMessage = ""
        val actual = uiStateMapper.map(
            horoscope = Horoscope("test", "test", "test", "test", "1", "2", "3"),
            errorMessage = emptyErrorMessage
        )
        val expected = UiState.Success("test", "test", "test", "test", "1", "2", "3")

        assertEquals(expected, actual)
    }

    @Test
    fun `map horoscope to ui state error`() {
        val uiStateMapper = UiStateMapper(HoroscopeMapper())

        val errorMessage = "No Internet connection"
        val actual = uiStateMapper.map(
            horoscope = Horoscope("test", "test", "test", "test", "1", "2", "3"),
            errorMessage = errorMessage
        )
        val expected = UiState.Error("No Internet connection")

        assertEquals(expected, actual)
    }

}
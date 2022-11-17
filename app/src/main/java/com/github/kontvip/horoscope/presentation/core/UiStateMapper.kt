package com.github.kontvip.horoscope.presentation.core

import com.github.kontvip.horoscope.data.model.Horoscope
import com.github.kontvip.horoscope.data.model.HoroscopeResult
import com.github.kontvip.horoscope.presentation.model.UiState

class UiStateMapper(private val horoscopeMapper: Horoscope.Mapper<UiState>) :
    HoroscopeResult.Mapper<UiState> {
    override fun map(horoscope: Horoscope, errorMessage: String): UiState =
        if (errorMessage.isEmpty())
            horoscope.map(horoscopeMapper)
        else
            UiState.Error(errorMessage)
}
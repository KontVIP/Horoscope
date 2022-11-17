package com.github.kontvip.horoscope.presentation.core

import com.github.kontvip.horoscope.data.model.Horoscope
import com.github.kontvip.horoscope.presentation.model.UiState

class HoroscopeMapper : Horoscope.Mapper<UiState> {

    override fun map(
        date: String,
        description: String,
        compatibility: String,
        mood: String,
        color: String,
        luckyNumber: String,
        luckyTime: String
    ): UiState =
        UiState.Success(date, description, compatibility, mood, color, luckyNumber, luckyTime)
}
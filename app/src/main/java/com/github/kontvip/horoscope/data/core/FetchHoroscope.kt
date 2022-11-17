package com.github.kontvip.horoscope.data.core

import com.github.kontvip.horoscope.data.model.HoroscopeResult
import com.github.kontvip.horoscope.domain.model.Day

interface HoroscopeData {
    suspend fun horoscopeData(sign: String, day: Day): HoroscopeResult
}
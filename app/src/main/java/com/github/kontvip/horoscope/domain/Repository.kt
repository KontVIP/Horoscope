package com.github.kontvip.horoscope.domain

import com.github.kontvip.horoscope.domain.model.Day
import com.github.kontvip.horoscope.data.model.HoroscopeResult

interface Repository {

    fun saveSign(sign: String)

    fun fetchSign(): String

    suspend fun fetchHoroscope(sign: String, day: Day): HoroscopeResult
}
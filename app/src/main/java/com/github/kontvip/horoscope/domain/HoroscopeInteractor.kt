package com.github.kontvip.horoscope.domain

import com.github.kontvip.horoscope.domain.model.Day
import com.github.kontvip.horoscope.data.model.HoroscopeResult

interface HoroscopeInteractor {

    fun saveSign(sign: String)

    fun fetchSign(): String

    suspend fun fetchHoroscope(sign: String, day: Day): HoroscopeResult

    class Base(
        private val repository: Repository
    ) : HoroscopeInteractor {

        override fun saveSign(sign: String): Unit = repository.saveSign(sign)

        override fun fetchSign(): String = repository.fetchSign()

        override suspend fun fetchHoroscope(sign: String, day: Day): HoroscopeResult =
            repository.fetchHoroscope(sign, day)
    }

}
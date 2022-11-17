package com.github.kontvip.horoscope.data.cache

import com.github.kontvip.horoscope.data.model.Horoscope
import com.github.kontvip.horoscope.data.model.HoroscopeResult

interface ToHoroscopeMapper : HoroscopeResult.Mapper<Horoscope> {

    class Base : ToHoroscopeMapper {
        override fun map(horoscope: Horoscope, errorMessage: String): Horoscope = horoscope
    }

}
package com.github.kontvip.horoscope.data.cache

import com.github.kontvip.horoscope.data.model.Horoscope
import com.github.kontvip.horoscope.data.model.HoroscopeCache
import com.github.kontvip.horoscope.domain.model.Day

interface ToCacheMapper : Horoscope.Mapper<HoroscopeCache> {

    class Base(private val day: Day, private val sign: String) : ToCacheMapper {
        override fun map(
            date: String,
            description: String,
            compatibility: String,
            mood: String,
            color: String,
            luckyNumber: String,
            luckyTime: String
        ): HoroscopeCache = HoroscopeCache(
            day.day(),
            day.date(),
            sign,
            date,
            description,
            compatibility,
            mood,
            color,
            luckyNumber,
            luckyTime
        )
    }

}
package com.github.kontvip.horoscope.data.cache

import com.github.kontvip.horoscope.core.Mapper
import com.github.kontvip.horoscope.data.model.Horoscope
import com.github.kontvip.horoscope.data.model.HoroscopeCache
import com.github.kontvip.horoscope.data.model.HoroscopeResult

interface CacheToHoroscopeResultMapper : Mapper<HoroscopeResult, HoroscopeCache> {

    class Base : CacheToHoroscopeResultMapper {
        override fun map(source: HoroscopeCache): HoroscopeResult =
            HoroscopeResult.Success(
                Horoscope(
                    source.date,
                    source.description,
                    source.compatibility,
                    source.mood,
                    source.color,
                    source.luckyNumber,
                    source.luckyTime
                )
            )
    }

}
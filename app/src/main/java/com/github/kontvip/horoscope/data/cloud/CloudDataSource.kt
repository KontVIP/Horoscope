package com.github.kontvip.horoscope.data.cloud

import com.github.kontvip.horoscope.core.Mapper
import com.github.kontvip.horoscope.data.core.HoroscopeData
import com.github.kontvip.horoscope.domain.model.Day
import com.github.kontvip.horoscope.data.model.Horoscope
import com.github.kontvip.horoscope.data.model.HoroscopeResult
import retrofit2.Response

interface CloudDataSource : HoroscopeData {

    class Base(
        private val horoscopeApi: HoroscopeApi,
        private val successMapper: Mapper<HoroscopeResult, Response<Horoscope>>,
        private val errorMapper: Mapper<HoroscopeResult, Exception>
    ) : CloudDataSource {
        override suspend fun horoscopeData(sign: String, day: Day): HoroscopeResult =
            try {
                successMapper.map(horoscopeApi.getHoroscope(sign, day.day()))
            } catch (e: Exception) {
                errorMapper.map(e)
            }
    }

}
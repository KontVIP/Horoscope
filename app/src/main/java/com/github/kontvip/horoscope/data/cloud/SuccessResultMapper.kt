package com.github.kontvip.horoscope.data.cloud

import com.github.kontvip.horoscope.core.Mapper
import com.github.kontvip.horoscope.data.model.HoroscopeResult
import com.github.kontvip.horoscope.data.model.Horoscope
import retrofit2.Response

interface SuccessResultMapper : Mapper<HoroscopeResult, Response<Horoscope>> {

    class Base : SuccessResultMapper {

        override fun map(source: Response<Horoscope>): HoroscopeResult {
            val result = source.body()
            return if (result == null)
                HoroscopeResult.Error(source.errorBody()?.string() ?: "")
            else
                HoroscopeResult.Success(result)
        }
    }

}
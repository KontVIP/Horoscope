package com.github.kontvip.horoscope.data.cloud

import com.github.kontvip.horoscope.R
import com.github.kontvip.horoscope.core.Mapper
import com.github.kontvip.horoscope.core.ResourceProvider
import com.github.kontvip.horoscope.data.model.HoroscopeResult
import java.net.UnknownHostException

interface ErrorResultMapper : Mapper<HoroscopeResult, Exception> {

    class Base(private val resourceProvider: ResourceProvider) : ErrorResultMapper {
        override fun map(source: Exception): HoroscopeResult =
            if (source is UnknownHostException)
                HoroscopeResult.Error(resourceProvider.string(R.string.no_internet))
            else
                HoroscopeResult.Error(source.message.toString())

    }
}
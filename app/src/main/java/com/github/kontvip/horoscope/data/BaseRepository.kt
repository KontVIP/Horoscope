package com.github.kontvip.horoscope.data

import com.github.kontvip.horoscope.core.Mapper
import com.github.kontvip.horoscope.data.cache.CacheDataSource
import com.github.kontvip.horoscope.data.cloud.CloudDataSource
import com.github.kontvip.horoscope.data.model.Horoscope
import com.github.kontvip.horoscope.data.model.HoroscopeCache
import com.github.kontvip.horoscope.data.model.HoroscopeResult
import com.github.kontvip.horoscope.domain.Repository
import com.github.kontvip.horoscope.domain.model.Day

class BaseRepository(
    private val cloudDataSource: CloudDataSource,
    private val cacheDataSource: CacheDataSource,
    private val toHoroscopeMapper: HoroscopeResult.Mapper<Horoscope>,
    private val cacheToHoroscopeResultMapper: Mapper<HoroscopeResult, HoroscopeCache>
) : Repository {

    override fun saveSign(sign: String): Unit = cacheDataSource.saveSign(sign)

    override fun fetchSign(): String = cacheDataSource.fetchSign()

    override suspend fun fetchHoroscope(sign: String, day: Day): HoroscopeResult =
        if (cacheDataSource.contains(sign, day)) {
            val cacheData = cacheDataSource.horoscopeData(sign, day)
            cacheToHoroscopeResultMapper.map(cacheData)
        } else {
            val cloudData = cloudDataSource.horoscopeData(sign, day)
            cloudData.handleCache {
                cacheDataSource.saveHoroscope(cloudData.map(toHoroscopeMapper), sign, day)
            }
            cloudData
        }

}
package com.github.kontvip.horoscope.data

import com.github.kontvip.horoscope.data.cache.CacheDataSource
import com.github.kontvip.horoscope.data.cache.CacheToHoroscopeResultMapper
import com.github.kontvip.horoscope.data.cache.ToCacheMapper
import com.github.kontvip.horoscope.data.cache.ToHoroscopeMapper
import com.github.kontvip.horoscope.data.cloud.CloudDataSource
import com.github.kontvip.horoscope.data.model.Horoscope
import com.github.kontvip.horoscope.data.model.HoroscopeCache
import com.github.kontvip.horoscope.data.model.HoroscopeResult
import com.github.kontvip.horoscope.domain.model.Day
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BaseRepositoryTest {

    private lateinit var cloudDataSource: FakeCloudDataSource
    private lateinit var cacheDataSource: FakeCacheDataSource
    private lateinit var toHoroscopeMapper: ToHoroscopeMapper
    private lateinit var cacheToHoroscopeResultMapper: CacheToHoroscopeResultMapper

    @Before
    fun setup() {
        cloudDataSource = FakeCloudDataSource()
        cacheDataSource = FakeCacheDataSource()
        toHoroscopeMapper = ToHoroscopeMapper.Base()
        cacheToHoroscopeResultMapper = CacheToHoroscopeResultMapper.Base()
    }

    @Test
    fun `fetch horoscope with cache`(): Unit = runBlocking {
        val repository = BaseRepository(
            cloudDataSource,
            cacheDataSource,
            toHoroscopeMapper,
            cacheToHoroscopeResultMapper
        )

        cacheDataSource.hasCache = true
        cacheDataSource.saveHoroscope(Horoscope("test", "test", "test", "test", "test", "test", "test"), "aries", Day.Today())
        val expected = HoroscopeResult.Success(Horoscope("test", "test", "test", "test", "test", "test", "test"))
        val actual = repository.fetchHoroscope("aries", Day.Today())
        assertEquals(expected, actual)
    }

    @Test
    fun `fetch horoscope without cache`(): Unit = runBlocking {
        val repository = BaseRepository(
            cloudDataSource,
            cacheDataSource,
            toHoroscopeMapper,
            cacheToHoroscopeResultMapper
        )

        cacheDataSource.hasCache = false
        val actualCloudHoroscope = repository.fetchHoroscope("aries", Day.Today())
        val expectedCloudHoroscope = HoroscopeResult.Success(Horoscope("test", "test", "test", "test", "test", "test", "test"))
        assertEquals(expectedCloudHoroscope, actualCloudHoroscope)

        val actualCacheResult = CacheToHoroscopeResultMapper.Base().map(cacheDataSource.horoscopeData("aries", Day.Today()))
        assertEquals(expectedCloudHoroscope, actualCacheResult)
    }

    @Test
    fun `sign caching`() {
        val repository = BaseRepository(
            cloudDataSource,
            cacheDataSource,
            toHoroscopeMapper,
            cacheToHoroscopeResultMapper
        )

        repository.saveSign("aries")
        val expected = "aries"
        val actual = repository.fetchSign()
        assertEquals(expected, actual)
    }

    private class FakeCloudDataSource : CloudDataSource {
        override suspend fun horoscopeData(sign: String, day: Day): HoroscopeResult {
            return HoroscopeResult.Success(Horoscope("test", "test", "test", "test", "test", "test", "test"))
        }
    }

    private class FakeCacheDataSource : CacheDataSource {

        var hasCache = false
        private lateinit var horoscopeCache: HoroscopeCache
        private var cachedSign: String = ""

        override fun saveSign(sign: String) {
            cachedSign = sign
        }

        override fun fetchSign(): String {
            return cachedSign
        }

        override fun saveHoroscope(horoscope: Horoscope, sign: String, day: Day) {
            horoscopeCache = horoscope.map(ToCacheMapper.Base(day, sign))
        }

        override suspend fun contains(sign: String, day: Day): Boolean = hasCache

        override suspend fun horoscopeData(sign: String, day: Day): HoroscopeCache {
            return horoscopeCache
        }
    }

}
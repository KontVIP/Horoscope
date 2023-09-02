package com.github.kontvip.horoscope.data.cache

import com.github.kontvip.horoscope.data.model.Horoscope
import com.github.kontvip.horoscope.data.model.HoroscopeCache
import com.github.kontvip.horoscope.data.model.HoroscopeResult
import org.junit.Assert.*
import org.junit.Test

class ToHoroscopeMapperTest {

    @Test
    fun `map cache to horoscope result`() {
        val cacheToHoroscopeResultMapper = CacheToHoroscopeResultMapper.Base()

        val expected = cacheToHoroscopeResultMapper.map(HoroscopeCache("test", "test", "test", "test", "test", "test", "test", "test", "test", "test"))
        val actual = HoroscopeResult.Success(Horoscope("test", "test", "test", "test", "test", "test", "test"))
        assertEquals(expected, actual)
    }

}
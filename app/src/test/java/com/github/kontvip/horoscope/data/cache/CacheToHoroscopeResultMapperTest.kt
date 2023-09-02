package com.github.kontvip.horoscope.data.cache

import com.github.kontvip.horoscope.data.model.Horoscope
import com.github.kontvip.horoscope.data.model.HoroscopeResult
import org.junit.Assert.*
import org.junit.Test

class CacheToHoroscopeResultMapperTest {

    @Test
    fun `map horoscope result to horoscope`() {
        val cacheToHoroscopeResultMapper = ToHoroscopeMapper.Base()

        val actual = HoroscopeResult.Success(Horoscope("test", "test", "test", "test", "test", "test", "test"))
            .map(cacheToHoroscopeResultMapper)
        val expected = Horoscope("test", "test", "test", "test", "test", "test", "test")
        assertEquals(expected, actual)
    }

}
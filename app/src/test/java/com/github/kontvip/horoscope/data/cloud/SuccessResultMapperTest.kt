package com.github.kontvip.horoscope.data.cloud

import com.github.kontvip.horoscope.data.model.Horoscope
import com.github.kontvip.horoscope.data.model.HoroscopeResult
import okhttp3.internal.EMPTY_RESPONSE
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class SuccessResultMapperTest {

    @Test
    fun `map horoscope response to success result`() {
        val successResultMapper = SuccessResultMapper.Base()

        val expected = HoroscopeResult.Success(Horoscope("test", "test", "test", "test", "1", "2", "3"))
        val actual = successResultMapper.map(Response.success(Horoscope("test", "test", "test", "test", "1", "2", "3")))

        assertEquals(expected, actual)
    }

    @Test
    fun `map horoscope response to error result`() {
        val successResultMapper = SuccessResultMapper.Base()

        val expected = HoroscopeResult.Error("")
        val actual = successResultMapper.map(Response.error(401, EMPTY_RESPONSE))

        assertEquals(expected, actual)
    }
}
package com.github.kontvip.horoscope.data.cloud

import com.github.kontvip.horoscope.R
import com.github.kontvip.horoscope.core.ResourceProvider
import com.github.kontvip.horoscope.data.model.HoroscopeResult
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class ErrorResultMapperTest {


    @Test
    fun `map UnknownHostException to horoscope result`() {
        val fakeResourceProvider = FakeResourceProvider()
        val errorResultMapper = ErrorResultMapper.Base(fakeResourceProvider)

        fakeResourceProvider.string = "No Internet connection"

        val expectedHoroscopeResult =  HoroscopeResult.Error("No Internet connection")
        val actualHoroscopeResult = errorResultMapper.map(UnknownHostException())
        assertEquals(expectedHoroscopeResult, actualHoroscopeResult)

        val expectedStringId = R.string.no_internet
        val actualStringId = fakeResourceProvider.id
        assertEquals(expectedStringId, actualStringId)
    }

    @Test
    fun `map exception to horoscope result`() {
        val errorResultMapper = ErrorResultMapper.Base(FakeResourceProvider())

        val expectedHoroscopeResult =  HoroscopeResult.Error("test")
        val actualHoroscopeResult = errorResultMapper.map(Exception("test"))
        assertEquals(expectedHoroscopeResult, actualHoroscopeResult)
    }

    private class FakeResourceProvider : ResourceProvider {
        var id: Int = -1
        var string = ""

        override fun string(id: Int): String {
            this.id = id
            return string
        }
    }
}
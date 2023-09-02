package com.github.kontvip.horoscope.domain

import com.github.kontvip.horoscope.data.model.Horoscope
import com.github.kontvip.horoscope.data.model.HoroscopeResult
import com.github.kontvip.horoscope.domain.model.Day
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


internal class HoroscopeInteractorTest {

    private lateinit var fakeRepository: FakeRepository

    @Before
    fun setup() {
        fakeRepository = FakeRepository()
    }

    @Test
    fun `horoscope interactor save and fetch sign`() {
        val horoscopeInteractor = HoroscopeInteractor.Base(fakeRepository)

        horoscopeInteractor.saveSign("aries")

        assertEquals("aries", fakeRepository.savedSign)
        assertEquals("aries", horoscopeInteractor.fetchSign())
    }

    @Test
    fun `horoscope interactor fetch sign`() = runBlocking {
        val horoscopeInteractor = HoroscopeInteractor.Base(fakeRepository)

        val expected = HoroscopeResult.Success(Horoscope("test", "test", "test", "test", "1", "2", "3"))
        val actual = horoscopeInteractor.fetchHoroscope("aries", Day.Today())
        assertEquals(expected, actual)
    }

    private class FakeRepository : Repository {

        var savedSign = ""

        override fun saveSign(sign: String) {
            savedSign = sign
        }

        override fun fetchSign(): String {
            return savedSign
        }

        override suspend fun fetchHoroscope(sign: String, day: Day): HoroscopeResult {
            return HoroscopeResult.Success(Horoscope("test", "test", "test", "test", "1", "2", "3"))
        }

    }

}
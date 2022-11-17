package com.github.kontvip.horoscope.data.cache

import android.content.Context
import com.github.kontvip.horoscope.data.model.Horoscope
import com.github.kontvip.horoscope.data.model.HoroscopeCache
import com.github.kontvip.horoscope.domain.model.Day
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

interface CacheDataSource {

    fun saveSign(sign: String)
    fun fetchSign(): String
    fun saveHoroscope(horoscope: Horoscope, sign: String, day: Day)
    suspend fun contains(sign: String, day: Day): Boolean
    suspend fun horoscopeData(sign: String, day: Day): HoroscopeCache

    class Base(
        private val dao: HoroscopeDao,
        context: Context
    ) : CacheDataSource {

        companion object {
            private const val SIGN_KEY_PREFERENCES = "SIGN_KEY_PREFERENCES"
            private const val SIGN_KEY = "SIGN_KEY"
        }

        private val prefs = context.getSharedPreferences(SIGN_KEY_PREFERENCES, Context.MODE_PRIVATE)

        override fun saveSign(sign: String): Unit = prefs.edit().putString(SIGN_KEY, sign).apply()

        override fun fetchSign(): String = prefs.getString(SIGN_KEY, "")!!

        override suspend fun horoscopeData(sign: String, day: Day): HoroscopeCache =
            dao.horoscope(sign, day.day()) ?: HoroscopeCache("", "", "", "", "", "", "", "", "", "")

        override suspend fun contains(sign: String, day: Day): Boolean {
            val data = dao.horoscope(sign, day.day()) ?: return false
            if (data.calendarDate != day.date()) {
                dao.deleteHoroscope(sign, day.day())
                return false
            }
            return true
        }

        override fun saveHoroscope(horoscope: Horoscope, sign: String, day: Day): Unit =
            dao.insert(horoscope.map(ToCacheMapper.Base(day, sign)))
    }

}
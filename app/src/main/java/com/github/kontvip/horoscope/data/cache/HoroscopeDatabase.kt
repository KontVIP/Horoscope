package com.github.kontvip.horoscope.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.kontvip.horoscope.data.model.HoroscopeCache

@Database(entities = [HoroscopeCache::class], version = 3)
abstract class HoroscopeDatabase : RoomDatabase() {

    abstract fun horoscopeDao() : HoroscopeDao
}
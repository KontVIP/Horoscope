package com.github.kontvip.horoscope.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "horoscope_table", primaryKeys = ["day", "sign"])
data class HoroscopeCache(
    @ColumnInfo(name = "day")
    val day: String,
    @ColumnInfo(name = "calendar_date")
    val calendarDate: String,
    @ColumnInfo(name = "sign")
    val sign: String,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "compatibility")
    val compatibility: String,
    @ColumnInfo(name = "mood")
    val mood: String,
    @ColumnInfo(name = "color")
    val color: String,
    @ColumnInfo(name = "lucky_number")
    val luckyNumber: String,
    @ColumnInfo(name = "lucky_time")
    val luckyTime: String
)
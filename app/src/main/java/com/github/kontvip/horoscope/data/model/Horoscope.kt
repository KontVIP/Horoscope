package com.github.kontvip.horoscope.data.model

import com.google.gson.annotations.SerializedName

data class Horoscope(
    @SerializedName("current_date")
    private val date: String,
    @SerializedName("description")
    private val description: String,
    @SerializedName("compatibility")
    private val compatibility: String,
    @SerializedName("mood")
    private val mood: String,
    @SerializedName("color")
    private val color: String,
    @SerializedName("lucky_number")
    private val luckyNumber: String,
    @SerializedName("lucky_time")
    private val luckyTime: String
) {

    interface Mapper<T> {
        fun map(
            date: String,
            description: String,
            compatibility: String,
            mood: String,
            color: String,
            luckyNumber: String,
            luckyTime: String
        ): T
    }

    fun <T> map(mapper: Mapper<T>): T =
        mapper.map(date, description, compatibility, mood, color, luckyNumber, luckyTime)

}
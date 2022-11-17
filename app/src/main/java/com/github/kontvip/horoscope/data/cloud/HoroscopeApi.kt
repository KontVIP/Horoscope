package com.github.kontvip.horoscope.data.cloud

import com.github.kontvip.horoscope.data.model.Horoscope
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface HoroscopeApi {

    @Headers("Content-Type: application/json")
    @POST("/")
    suspend fun getHoroscope(
        @Query("sign") sign: String,
        @Query("day") day: String
    ): Response<Horoscope>

}
package com.github.kontvip.horoscope.data.cloud

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    private val client = OkHttpClient.Builder().build()

    private var gson = GsonBuilder()
        .setLenient()
        .create()

    private val factRetrofit = Retrofit.Builder()
        .baseUrl("https://aztro.sameerkumar.website")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()


    fun <T> buildService(service: Class<T>): T {
        return factRetrofit.create(service)
    }

}
package com.github.kontvip.horoscope.di

import android.content.Context
import androidx.room.Room
import com.github.kontvip.horoscope.core.Mapper
import com.github.kontvip.horoscope.core.ResourceProvider
import com.github.kontvip.horoscope.data.BaseRepository
import com.github.kontvip.horoscope.data.cache.*
import com.github.kontvip.horoscope.data.cloud.*
import com.github.kontvip.horoscope.data.model.Horoscope
import com.github.kontvip.horoscope.data.model.HoroscopeCache
import com.github.kontvip.horoscope.data.model.HoroscopeResult
import com.github.kontvip.horoscope.domain.Repository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideCacheDataSource(
        @ApplicationContext context: Context,
        dao: HoroscopeDao
    ): CacheDataSource =
        CacheDataSource.Base(context = context, dao = dao)

    @Provides
    @Singleton
    fun provideRepository(
        cloudDataSource: CloudDataSource,
        cacheDataSource: CacheDataSource,
        toHoroscopeMapper: HoroscopeResult.Mapper<Horoscope>,
        cacheToHoroscopeResultMapper: Mapper<HoroscopeResult, HoroscopeCache>
    ): Repository = BaseRepository(
        cloudDataSource = cloudDataSource,
        cacheDataSource = cacheDataSource,
        toHoroscopeMapper = toHoroscopeMapper,
        cacheToHoroscopeResultMapper = cacheToHoroscopeResultMapper
    )

    @Provides
    @Singleton
    fun provideHoroscopeDao(@ApplicationContext context: Context): HoroscopeDao =
        Room.databaseBuilder(context, HoroscopeDatabase::class.java, "horoscope_database")
            .fallbackToDestructiveMigration()
            .build().horoscopeDao()

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://aztro.sameerkumar.website")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    @Provides
    @Singleton
    fun provideHoroscopeApi(retrofit: Retrofit): HoroscopeApi =
        retrofit.create(HoroscopeApi::class.java)

    @Provides
    @Singleton
    fun provideCloudDataSource(
        horoscopeApi: HoroscopeApi,
        successMapper: Mapper<HoroscopeResult, Response<Horoscope>>,
        errorMapper: Mapper<HoroscopeResult, Exception>
    ): CloudDataSource =
        CloudDataSource.Base(
            horoscopeApi = horoscopeApi,
            successMapper = successMapper,
            errorMapper = errorMapper
        )

    @Provides
    @Singleton
    fun provideSuccessResultMapper(): Mapper<HoroscopeResult, Response<Horoscope>> =
        SuccessResultMapper.Base()

    @Provides
    @Singleton
    fun provideErrorResultMapper(resourceProvider: ResourceProvider): Mapper<HoroscopeResult, Exception> =
        ErrorResultMapper.Base(resourceProvider = resourceProvider)

    @Provides
    @Singleton
    fun provideToHoroscopeMapper(): HoroscopeResult.Mapper<Horoscope> = ToHoroscopeMapper.Base()

    @Provides
    @Singleton
    fun provideCacheToHoroscopeResultMapper(): Mapper<HoroscopeResult, HoroscopeCache> =
        CacheToHoroscopeResultMapper.Base()

}
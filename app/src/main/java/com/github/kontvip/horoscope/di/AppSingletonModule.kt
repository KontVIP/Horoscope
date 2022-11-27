package com.github.kontvip.horoscope.di

import com.github.kontvip.horoscope.data.model.Horoscope
import com.github.kontvip.horoscope.data.model.HoroscopeResult
import com.github.kontvip.horoscope.presentation.core.Dispatchers
import com.github.kontvip.horoscope.presentation.core.HoroscopeMapper
import com.github.kontvip.horoscope.presentation.core.NavigationCommunication
import com.github.kontvip.horoscope.presentation.core.UiStateMapper
import com.github.kontvip.horoscope.presentation.model.UiState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppSingletonModule {

    @Provides
    @Singleton
    fun provideNavigationCommunication(): NavigationCommunication =
        NavigationCommunication.Base()

    @Provides
    @Singleton
    fun provideDispatchers(): Dispatchers = Dispatchers.Base()

    @Provides
    @Singleton
    fun provideUiStateMapper(horoscopeMapper: Horoscope.Mapper<UiState>): HoroscopeResult.Mapper<UiState> =
        UiStateMapper(horoscopeMapper = horoscopeMapper)

    @Provides
    @Singleton
    fun provideHoroscopeMapper(): Horoscope.Mapper<UiState> = HoroscopeMapper()
}
package com.github.kontvip.horoscope.di

import com.github.kontvip.horoscope.domain.HoroscopeInteractor
import com.github.kontvip.horoscope.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideHoroscopeInteractor(repository: Repository): HoroscopeInteractor =
        HoroscopeInteractor.Base(repository = repository)

}
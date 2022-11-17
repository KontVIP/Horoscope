package com.github.kontvip.horoscope.di

import com.github.kontvip.horoscope.domain.HoroscopeInteractor
import com.github.kontvip.horoscope.domain.Repository
import com.github.kontvip.horoscope.presentation.core.ProgressCommunication
import com.github.kontvip.horoscope.presentation.core.SignCommunication
import com.github.kontvip.horoscope.presentation.core.UiStateCommunication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideHoroscopeInteractor(repository: Repository): HoroscopeInteractor =
        HoroscopeInteractor.Base(repository = repository)

}
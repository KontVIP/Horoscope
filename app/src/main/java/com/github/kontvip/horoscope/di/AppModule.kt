package com.github.kontvip.horoscope.di

import com.github.kontvip.horoscope.presentation.core.ProgressCommunication
import com.github.kontvip.horoscope.presentation.core.SignCommunication
import com.github.kontvip.horoscope.presentation.core.UiStateCommunication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {

    @Provides
    fun provideMutableSignCommunication(
        progressCommunication: ProgressCommunication,
        uiStateCommunication: UiStateCommunication
    ): SignCommunication = SignCommunication.Base(
        progressCommunication = progressCommunication,
        uiStateCommunication = uiStateCommunication
    )

    @Provides
    fun provideProgressCommunication(): ProgressCommunication = ProgressCommunication.Base()

    @Provides
    fun provideUiStateCommunication(): UiStateCommunication = UiStateCommunication.Base()

}

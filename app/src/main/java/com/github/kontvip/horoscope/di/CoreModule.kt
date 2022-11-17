package com.github.kontvip.horoscope.di

import android.content.Context
import com.github.kontvip.horoscope.core.ResourceProvider
import com.github.kontvip.horoscope.presentation.core.BaseResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreModule {

    @Provides
    @Singleton
    fun provideResourceProvider(@ApplicationContext context: Context): ResourceProvider =
        BaseResourceProvider(context = context)

}
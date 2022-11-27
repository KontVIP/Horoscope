package com.github.kontvip.horoscope.presentation.screen.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.github.kontvip.horoscope.presentation.core.Communication
import com.github.kontvip.horoscope.core.Init
import com.github.kontvip.horoscope.domain.HoroscopeInteractor
import com.github.kontvip.horoscope.presentation.core.Navigation
import com.github.kontvip.horoscope.presentation.core.NavigationCommunication
import com.github.kontvip.horoscope.presentation.core.ScreenAction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigationCommunication: NavigationCommunication,
    private val interactor: HoroscopeInteractor
) : ViewModel(), Init, Communication.Observe<Navigation> {

    override fun init(isFirstRun: Boolean) {
        if (isFirstRun && interactor.fetchSign().isNotEmpty())
            navigationCommunication.map(Navigation.Base(ScreenAction.SelectSignToSignFragment()))
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<Navigation>) {
        navigationCommunication.observe(owner, observer)
    }

}
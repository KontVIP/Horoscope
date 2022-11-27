package com.github.kontvip.horoscope.presentation.screen.select

import androidx.lifecycle.ViewModel
import com.github.kontvip.horoscope.domain.HoroscopeInteractor
import com.github.kontvip.horoscope.presentation.core.Navigation
import com.github.kontvip.horoscope.presentation.core.NavigationCommunication
import com.github.kontvip.horoscope.presentation.core.ScreenAction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SelectSignViewModel @Inject constructor(
    private val navigationCommunication: NavigationCommunication,
    private val interactor: HoroscopeInteractor
) : ViewModel() {

    fun showSignFragment(sign: String) {
        interactor.saveSign(sign)
        navigationCommunication.map(Navigation.Base(ScreenAction.SelectSignToSignFragment()))
    }

}
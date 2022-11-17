package com.github.kontvip.horoscope.presentation.screen.sign

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kontvip.horoscope.data.model.HoroscopeResult
import com.github.kontvip.horoscope.domain.HoroscopeInteractor
import com.github.kontvip.horoscope.domain.model.Day
import com.github.kontvip.horoscope.presentation.core.Dispatchers
import com.github.kontvip.horoscope.presentation.core.ObserveSign
import com.github.kontvip.horoscope.presentation.core.SignCommunication
import com.github.kontvip.horoscope.presentation.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignViewModel @Inject constructor(
    private val communication: SignCommunication,
    private val interactor: HoroscopeInteractor,
    private val uiStateMapper: HoroscopeResult.Mapper<UiState>,
    private val dispatchers: Dispatchers
) : ViewModel(), ObserveSign {

    fun fetchHoroscope(day: Day) {
        viewModelScope.launch(dispatchers.io()) {
            communication.showProgress(View.VISIBLE)
            communication.showState(
                interactor.fetchHoroscope(fetchSign(), day).map(uiStateMapper)
            )
            communication.showProgress(View.GONE)
        }
    }

    fun fetchSign(): String = interactor.fetchSign()

    override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>): Unit =
        communication.observeProgress(owner, observer)

    override fun observeUiState(owner: LifecycleOwner, observer: Observer<UiState>): Unit =
        communication.observeUiState(owner, observer)
}
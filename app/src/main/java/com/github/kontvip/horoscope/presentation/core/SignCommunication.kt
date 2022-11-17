package com.github.kontvip.horoscope.presentation.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.github.kontvip.horoscope.domain.model.Day
import com.github.kontvip.horoscope.presentation.model.UiState

interface SignCommunication : ObserveSign {

    fun showProgress(show: Int)
    fun showState(uiState: UiState)

    class Base(
        private val progressCommunication: ProgressCommunication,
        private val uiStateCommunication: UiStateCommunication
    ) : SignCommunication {
        override fun showProgress(show: Int): Unit = progressCommunication.map(show)

        override fun showState(uiState: UiState): Unit = uiStateCommunication.map(uiState)

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>): Unit =
            progressCommunication.observe(owner, observer)

        override fun observeUiState(owner: LifecycleOwner, observer: Observer<UiState>): Unit =
            uiStateCommunication.observe(owner, observer)
    }
}

interface ObserveSign {
    fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>)
    fun observeUiState(owner: LifecycleOwner, observer: Observer<UiState>)
}

interface ProgressCommunication : Communication.Mutable<Int> {
    class Base : Communication.Post<Int>(), ProgressCommunication
}

interface UiStateCommunication : Communication.Mutable<UiState> {
    class Base : Communication.Post<UiState>(), UiStateCommunication
}

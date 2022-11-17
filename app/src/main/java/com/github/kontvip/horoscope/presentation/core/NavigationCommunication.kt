package com.github.kontvip.horoscope.presentation.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface NavigationCommunication {

    interface Observe : Communication.Observe<Navigation>

    interface Mutate : Communication.Mutate<Navigation>

    interface Mutable : Observe, Mutate

    class Base(private val liveData: MutableLiveData<Navigation> = MutableLiveData()) : Mutable {
        override fun observe(owner: LifecycleOwner, observer: Observer<Navigation>) {
            liveData.observe(owner, observer)
        }

        override fun map(source: Navigation) {
            liveData.value = source
        }
    }
}
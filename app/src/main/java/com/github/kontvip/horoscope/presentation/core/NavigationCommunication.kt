package com.github.kontvip.horoscope.presentation.core

interface NavigationCommunication : Communication.Mutable<Navigation> {
    class Base : Communication.Ui<Navigation>(), NavigationCommunication
}
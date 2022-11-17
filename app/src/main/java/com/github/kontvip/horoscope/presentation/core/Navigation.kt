package com.github.kontvip.horoscope.presentation.core

import androidx.navigation.NavController

interface Navigation {

    fun navigate(navController: NavController)

    class Base(private val screenAction: ScreenAction) : Navigation {

        override fun navigate(navController: NavController) {
            screenAction.navigate(navController)
        }
    }

}
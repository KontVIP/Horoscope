package com.github.kontvip.horoscope.presentation.core

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.github.kontvip.horoscope.R

sealed class ScreenAction(private val bundle: Bundle) {

    fun navigate(navController: NavController): Unit = navController.navigate(id(), bundle)

    abstract fun id(): Int

    class SelectSignToSignFragment(bundle: Bundle = bundleOf()) : ScreenAction(bundle) {
        override fun id(): Int = R.id.action_SelectSignFragment_to_SignFragment
    }

    class SignToSelectSignFragment(bundle: Bundle = bundleOf()) : ScreenAction(bundle) {
        override fun id(): Int = R.id.action_SignFragment_to_SelectSignFragment
    }

}
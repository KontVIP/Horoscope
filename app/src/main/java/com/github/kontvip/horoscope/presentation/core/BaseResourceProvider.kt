package com.github.kontvip.horoscope.presentation.core

import android.content.Context
import androidx.annotation.StringRes
import com.github.kontvip.horoscope.core.ResourceProvider

class BaseResourceProvider(private val context: Context) : ResourceProvider {

    override fun string(@StringRes id: Int): String = context.getString(id)
}
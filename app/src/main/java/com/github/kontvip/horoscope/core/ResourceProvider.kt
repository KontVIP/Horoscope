package com.github.kontvip.horoscope.core

interface ResourceProvider {

    fun string(id: Int): String
}
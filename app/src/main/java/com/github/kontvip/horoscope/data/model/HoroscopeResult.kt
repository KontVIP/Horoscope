package com.github.kontvip.horoscope.data.model


sealed class HoroscopeResult(private val horoscope: Horoscope, private val errorMessage: String) {

    interface Mapper<T> {
        fun map(horoscope: Horoscope, errorMessage: String): T
    }

    fun <T> map(mapper: Mapper<T>): T = mapper.map(horoscope, errorMessage)

    abstract fun handleCache(block: () -> Unit)

    data class Success(private val horoscope: Horoscope) : HoroscopeResult(horoscope, "") {
        override fun handleCache(block: () -> Unit) {
            block.invoke()
        }
    }

    data class Error(private val errorMessage: String) :
        HoroscopeResult(Horoscope("", "", "", "", "", "", ""), errorMessage) {
        override fun handleCache(block: () -> Unit) = Unit
    }
}
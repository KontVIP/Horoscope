package com.github.kontvip.horoscope.domain.model

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

sealed interface Day {

    fun day(): String
    fun isSameDay(day: Day): Boolean
    fun date(): String

    class WithId(private val id: Int) : DayWithId {
        override fun day(): Day = when (id) {
            0 -> Yesterday()
            1 -> Today()
            2 -> Tomorrow()
            else -> throw IllegalStateException("No day with id: $id")
        }
    }

    abstract class Abstract : Day {
        protected abstract val day: String
        protected abstract val dayDifference: Long

        override fun day(): String = day

        override fun isSameDay(day: Day) = day == this

        override fun date(): String {
            val date = Date(System.currentTimeMillis()-TimeUnit.DAYS.toMillis(dayDifference))
            return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)
        }
    }

    class Yesterday : Abstract() {
        override val day: String = "yesterday"
        override val dayDifference: Long = 1
    }

    class Today : Abstract() {
        override val day: String = "today"
        override val dayDifference: Long = 0
    }

    class Tomorrow : Abstract() {
        override val day: String = "tomorrow"
        override val dayDifference: Long = -1
    }
}

interface DayWithId {
    fun day(): Day
}
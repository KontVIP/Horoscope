package com.github.kontvip.horoscope.presentation.select

import com.github.kontvip.horoscope.presentation.screen.select.SelectSignViewModel
import org.junit.Assert.*
import org.junit.Test

class SelectSignViewModelTest {


    @Test
    fun select_sign() {
        val viewModel = SelectSignViewModel()
        val communication = SelectSignCommunicationTest()

        viewModel.init(isFirstRun = true)

        viewModel.selectSign("Aries")
        communication.showProgress(true)
        assertEquals(1, communication.timesProgressCalled.size)
        assertEquals(true, communication.timesProgressCalled[0])

    }

    private class SelectSignCommunicationTest() : SignCommunication {

        val timesProgressCalled = mutableListOf<Boolean>()

        override fun showProgress(show: Boolean) {
            timesProgressCalled.add(show)
        }

    }

}
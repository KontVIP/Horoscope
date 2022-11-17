package com.github.kontvip.horoscope.presentation.screen.sign.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.github.kontvip.horoscope.databinding.FragmentDayBinding
import com.github.kontvip.horoscope.domain.model.Day
import com.github.kontvip.horoscope.presentation.core.BaseFragment
import com.github.kontvip.horoscope.presentation.screen.sign.SignViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseDayFragment : BaseFragment<FragmentDayBinding>() {

    protected val viewModel by viewModels<SignViewModel>()
    protected abstract val day: Day

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchHoroscope(day)
        viewModel.observeUiState(requireActivity()) { it.applyUi(binding) }
        viewModel.observeProgress(requireActivity()) { binding.progressBar.visibility = it }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDayBinding = FragmentDayBinding.inflate(inflater, container, false)

}
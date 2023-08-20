package com.github.kontvip.horoscope.presentation.screen.select

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.viewModels
import com.github.kontvip.horoscope.databinding.FragmentSelectSignBinding
import com.github.kontvip.horoscope.presentation.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectSignFragment : BaseFragment<FragmentSelectSignBinding>() {

    private val viewModel by viewModels<SelectSignViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.gridLayout.children.forEach {
            it.setOnClickListener { view ->
                viewModel.showSignFragment(view.tag.toString())
            }
        }
    }

    override fun initBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentSelectSignBinding = FragmentSelectSignBinding.inflate(inflater, container, false)
}
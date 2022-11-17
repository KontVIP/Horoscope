package com.github.kontvip.horoscope.presentation.screen.sign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import com.github.kontvip.horoscope.R
import com.github.kontvip.horoscope.databinding.FragmentSignBinding
import com.github.kontvip.horoscope.presentation.core.BaseFragment
import com.github.kontvip.horoscope.presentation.screen.sign.pager.PagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignFragment : BaseFragment<FragmentSignBinding>() {

    private val viewModel by viewModels<SignViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signText.text = viewModel.fetchSign()
        binding.viewPager.adapter = PagerAdapter(requireActivity())
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            when (pos) {
                0 -> tab.text = getString(R.string.yesterday)
                1 -> tab.text = getString(R.string.today)
                2 -> tab.text = getString(R.string.tomorrow)
            }
        }.attach()
        binding.viewPager.doOnPreDraw { binding.viewPager.currentItem = 1 }
    }

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSignBinding =
        FragmentSignBinding.inflate(inflater, container, false)
}
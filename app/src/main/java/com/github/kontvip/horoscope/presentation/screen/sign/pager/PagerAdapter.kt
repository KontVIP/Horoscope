package com.github.kontvip.horoscope.presentation.screen.sign.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.kontvip.horoscope.domain.model.Day

class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment = DayFragment(Day.WithId(position).day())

    override fun getItemCount(): Int = 3

}
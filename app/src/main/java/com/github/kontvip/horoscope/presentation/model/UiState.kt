package com.github.kontvip.horoscope.presentation.model

import android.view.View
import com.github.kontvip.horoscope.databinding.FragmentDayBinding

sealed class UiState {

    abstract fun applyUi(binding: FragmentDayBinding)

    data class Success(
        private val date: String,
        private val description: String,
        private val compatibility: String,
        private val mood: String,
        private val color: String,
        private val luckyNumber: String,
        private val luckyTime: String
    ) : UiState() {
        override fun applyUi(binding: FragmentDayBinding) {
            binding.errorTextView.visibility = View.GONE
            binding.dateTextView.text = date
            binding.descriptionTextView.text = description
            binding.compabilityTextView.text = compatibility
            binding.moodTextView.text = mood
            binding.colorTextView.text = color
            binding.luckyNumberTextView.text = luckyNumber
            binding.luckyTimeTextView.text = luckyTime
        }
    }

    data class Error(private val errorMessage: String) : UiState() {
        override fun applyUi(binding: FragmentDayBinding) {
            binding.errorTextView.apply {
                visibility = View.VISIBLE
                text = errorMessage
            }
        }
    }

}
package com.oleks.weather.ui.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oleks.weather.data.di.WeatherRepo

class WeatherViewFactory constructor(
    private val repository: WeatherRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(WeatherView::class.java)) {
            WeatherView(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
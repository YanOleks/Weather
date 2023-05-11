package com.oleks.weather.ui.overview

import android.util.Log
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.core.content.res.TypedArrayUtils.getText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oleks.weather.R
import com.oleks.weather.data.api.ApiHelper
import com.oleks.weather.data.api.model.WeatherInfo
import kotlinx.coroutines.launch

class WeatherViewModel(private val apiHelper: ApiHelper) : ViewModel() {
    init {
        fetchWeather()
    }

    private fun fetchWeather() {
        viewModelScope.launch {
            try {
                val weatherInfo = apiHelper.getWeather(50.0, 43.0)
                Log.d("TEST", "ol")
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())

            }
        }
    }

    data class CurrentWeather(
        val temperature: Int,
        val lowest: Int,
        val highest: Int,
        val feels: Int,
        val img: Int
    )

    val d = R.string.feels
}
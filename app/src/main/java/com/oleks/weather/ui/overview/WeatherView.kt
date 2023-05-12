package com.oleks.weather.ui.overview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oleks.weather.data.di.WeatherRepo
import com.oleks.weather.data.model.WeatherInfo
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

//TODO: latitude, longitude

class WeatherView constructor(
    private val weatherRepo: WeatherRepo
): ViewModel() {
    private val hours = 24

    private var latitude: Double = 50.4375
    private var longitude: Double = 30.5

    val errorMessage = MutableLiveData<String>()
    //private lateinit var weather: WeatherInfo
    val currentWeather = MutableLiveData<CurrentWeather>()
    var hourWeather = MutableLiveData<List<HourWeather>>()
    private var job: Job? = null

    val loading = MutableLiveData(true)

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getWeather() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = weatherRepo.getWeather(latitude, longitude)
            withContext(Dispatchers.Main) {
                val weather = response.body()
                if (response.isSuccessful && weather != null) {
                    currentWeather.postValue(getCurrentWeather(weather))
                    hourWeather.postValue(getHourWeather(weather))
                    loading.value = false
                } else {
                    onError("Error : ${response.message()}")
                }
            }
        }
    }

    private fun onError(message: String){
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    data class CurrentWeather(
        val temperature: Int,
        val apparent: Int,
        val lowest: Int,
        val highest: Int,
        /*TODO: val state: String,
        val img: Int*/
    )

    private fun getCurrentWeather(weather: WeatherInfo): CurrentWeather {
        val time = weather.currentWeather.time
        return CurrentWeather(
            weather.currentWeather.temperature.roundToInt(),
            getApparent(weather.hourly.apparentTemperature, time),
            weather.daily.temperature2mMin[0].roundToInt(),
            weather.daily.temperature2mMax[0].roundToInt()
        )
    }

    private fun getIndexFromTime(time: String): Int{
        return time.substring(11, 13).toInt()
    }
    private fun getApparent(values: List<Double>, time: String): Int{
        val index = getIndexFromTime(time)
        return values[index].roundToInt()
    }

    data class HourWeather(
        val time: String,
        val temperature: Int,
        //TODO: val img: Int
    )

    private fun getHourWeather(weather: WeatherInfo): List<HourWeather>{
        val list = mutableListOf<HourWeather>()
        var time = getIndexFromTime(weather.currentWeather.time)
        val temperatureList = weather.hourly.temperature2m

        for (i in 0..hours){
            list.add(HourWeather(
                "${ if (time < 24) time else time - 24}:00",
                temperatureList[time].roundToInt()
            ))
            time++
        }

        return list.toList()
    }

}
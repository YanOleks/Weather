package com.oleks.weather.ui.overview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oleks.weather.LATITUDE
import com.oleks.weather.LONGITUDE
import com.oleks.weather.R
import com.oleks.weather.data.di.WeatherRepo
import com.oleks.weather.data.openmeteo.model.WeatherInfo
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.roundToInt

//TODO: latitude, longitude

class WeatherView constructor(
    private val weatherRepo: WeatherRepo
): ViewModel() {
    private val hours = 23
    private val days = 6

    var latitude: Double = LATITUDE
    var longitude: Double = LONGITUDE
    var place: String? = null

    val errorMessage = MutableLiveData<String>()
    //private lateinit var weather: WeatherInfo
    val currentWeather = MutableLiveData<CurrentWeather>()
    var hourWeather = MutableLiveData<List<HourWeather>>()
    var weekWeather = MutableLiveData<List<Day>>()
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
                    weekWeather.postValue(getWeek(weather))
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
        val state: Int,
        val img: Int
    )

    private fun getCurrentWeather(weather: WeatherInfo): CurrentWeather {
        val time = weather.currentWeather.time
        val index = getIndexFromTime(time)

        val (stateString, stateImg) = getState(weather.hourly.weatherCode[index])
        return CurrentWeather(
            weather.currentWeather.temperature.roundToInt(),
            getApparent(weather.hourly.apparentTemperature, index),
            weather.daily.temperature2mMin[0].roundToInt(),
            weather.daily.temperature2mMax[0].roundToInt(),
            stateString,
            stateImg
        )
    }

    private fun getIndexFromTime(time: String): Int{
        return time.substring(11, 13).toInt()
    }
    private fun getState(code: Int): Pair<Int, Int> {
        return when(code){
            0 -> Pair(R.string.clear, R.drawable.sun)
            1 -> Pair(R.string.mainly, R.drawable.partly_cloudy)
            2 -> Pair(R.string.partly, R.drawable.partly_cloudy)
            3 -> Pair(R.string.overcast, R.drawable.cloud)

            51, 53, 55 -> Pair(R.string.drizzle, R.drawable.cloudy)
            61, 63, 65 -> Pair(R.string.rain, R.drawable.raining)
            else -> Pair(0,0)
        }
    }
    private fun getApparent(values: List<Double>, index: Int): Int{
        return values[index].roundToInt()
    }

    data class HourWeather(
        val time: String,
        val temperature: Int,
        val img: Int
    )

    private fun getHourWeather(weather: WeatherInfo): List<HourWeather>{
        val list = mutableListOf<HourWeather>()
        var time = getIndexFromTime(weather.currentWeather.time)
        val temperatureList = weather.hourly.temperature2m
        for (i in 0..hours){
            val (_, img) = getState(weather.hourly.weatherCode[time])
            list.add(HourWeather(
                "${ if (time < 24) time else time - 24}:00",
                temperatureList[time].roundToInt(),
                img
            ))
            time++
        }

        return list.toList()
    }
    data class Day(
        val date: String,
        val maxTemperature: Int,
        val minTemperature: Int,
        val state: Int,
        val img: Int
    )

    private fun getWeek(weather: WeatherInfo): List<Day>{

        val list = mutableListOf<Day>()
        for (i in 0..days){
            val (state, img) = getState(weather.daily.weatherCode[i])
            list.add(

                Day(
                    date = getDate(weather.daily.time[i]),
                    maxTemperature = weather.daily.temperature2mMax[i].roundToInt(),
                    minTemperature = weather.daily.temperature2mMin[i].roundToInt(),
                    state = state,
                    img = img
                )
            )
        }

        return list.toList()
    }

    private fun getDate(str: String): String{
        //val date = LocalDate.parse(str, DateTimeFormatter.ISO_DATE)
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val outputFormat  = SimpleDateFormat("d MMMM", Locale(Locale.getDefault().language))
        val date = inputFormat.parse(str)
        return outputFormat.format(date)
    }
}
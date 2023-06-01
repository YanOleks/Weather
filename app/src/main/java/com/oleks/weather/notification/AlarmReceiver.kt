package com.oleks.weather.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.oleks.weather.data.di.WeatherRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class AlarmReceiver : BroadcastReceiver() {
    private var latitude: Double = 50.4375
    private var longitude: Double = 30.5

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "SetAlarmClock"){
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = WeatherRepo().getWeather(latitude, longitude)
                    val weather = response.body()
                    if (response.isSuccessful && weather != null) {
                        Log.i("Success", weather.currentWeather.temperature.toString())
                        val notification = Notification(context, weather)
                        notification.notification()
                    }

                } catch (e: Exception){
                    Log.e("AlarmReceiver", e.message.toString())
                }
            }
        }
    }
}
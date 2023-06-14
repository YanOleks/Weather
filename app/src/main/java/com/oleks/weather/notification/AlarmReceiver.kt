package com.oleks.weather.notification

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.oleks.weather.LATITUDE
import com.oleks.weather.LONGITUDE
import com.oleks.weather.R
import com.oleks.weather.data.di.WeatherRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val notificationID = 1
const val channelID = "Weather"
class Notification : BroadcastReceiver(){
    override fun onReceive(context: Context, intent: Intent) {

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        CoroutineScope(Dispatchers.Default).launch {
            val response = WeatherRepo().getWeather(LATITUDE, LONGITUDE)

            if (response.isSuccessful){
                val weather = response.body()!!
                val (state, img) = getState(weather.currentWeather.weatherCode)
                val strState = context.resources.getString(state)
                val high = weather.daily.temperature2mMax[0]
                val low = weather.daily.temperature2mMin[0]
                val str = "↑$high° / ↓$low°  $strState"
                val priority = if (intent.getBooleanExtra("sound", false)) {
                    NotificationCompat.PRIORITY_HIGH
                } else NotificationCompat.PRIORITY_LOW
                val notification = NotificationCompat.Builder(context, channelID)
                    .setSmallIcon(R.drawable.sun)
                    .setContentTitle(context.resources.getString(R.string.notification_today))
                    .setContentText(str)
                    .setLargeIcon(BitmapFactory.decodeResource(context.resources, img))
                    .setPriority(priority)
                    .build()
                manager.notify(notificationID, notification)
            }
        }
    }
    private fun getState(code: Int): Pair<Int, Int> {
        return when(code){
            0 -> Pair(R.string.clear, R.drawable.sun)
            1 -> Pair(R.string.mainly, R.drawable.partly_cloudy)
            2 -> Pair(R.string.partly, R.drawable.partly_cloudy)
            3 -> Pair(R.string.overcast, R.drawable.cloud)
            45, 48 -> Pair(R.string.fog, R.drawable.fog)
            71, 73, 75, 85, 86 -> Pair(R.string.snow, R.drawable.snowfall)
            95, 96, 99 -> Pair(R.string.thunder, R.drawable.storm)
            51, 53, 55, 56,57 -> Pair(R.string.drizzle, R.drawable.cloudy)
            61, 63, 65, 66, 67 -> Pair(R.string.rain, R.drawable.raining)
            80, 81, 82 -> Pair(R.string.shower, R.drawable.shower)
            77 -> Pair(R.string.grains, R.drawable.snowfall)
            else -> Pair(R.string.rain,R.drawable.ic_test)
        }
    }
}
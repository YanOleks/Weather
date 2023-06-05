package com.oleks.weather.notification

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.oleks.weather.R
import com.oleks.weather.data.di.WeatherRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val notificationID = 1
const val channelID = "channel1"
class Notification : BroadcastReceiver(){
    var LATITUDE:Double = 50.44
    var LONGITUDE:Double =30.51
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
                val str = "$high° / $low° · $strState"
                val notification = NotificationCompat.Builder(context, channelID)
                    .setSmallIcon(R.drawable.sun)
                    .setContentTitle(context.resources.getString(R.string.notification_today))
                    .setContentText(str)
                    .setLargeIcon(BitmapFactory.decodeResource(context.resources, img))
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

            51, 53, 55 -> Pair(R.string.drizzle, R.drawable.cloudy)
            61, 63, 65, 80, 81, 82 -> Pair(R.string.rain, R.drawable.raining)
            else -> Pair(R.string.rain,R.drawable.ic_test)
        }
    }
}
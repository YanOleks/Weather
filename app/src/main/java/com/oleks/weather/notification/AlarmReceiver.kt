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

            val notification = NotificationCompat.Builder(context, channelID)
                .setSmallIcon(R.drawable.sun)
                .setContentTitle("Fish")
                .setContentText("")
                .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.sun))
                .build()
            manager.notify(notificationID, notification)
        }
    }
}
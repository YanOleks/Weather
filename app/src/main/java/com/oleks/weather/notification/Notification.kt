package com.oleks.weather.notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.oleks.weather.R
import com.oleks.weather.data.openmeteo.model.WeatherInfo

class Notification(
    private val context: Context,
    weatherInfo: WeatherInfo
) {
    private val channelID = "weatherReport"
    private val channelName = "Weather Report"

    private var builder = NotificationCompat.Builder(context, channelID)
        .setSmallIcon(R.drawable.ic_test)
        .setContentTitle(context.resources.getString(R.string.app_name))
        .setContentText("Сьогодні: Мінливо ↓14° ↑26°")//TODO: add
        .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.cloudy))
        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    fun notification(){
        createNotificationChannel()

        with(NotificationManagerCompat.from(context)){
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                //  TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            notify(3,builder.build())
        }
    }
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        val name = channelName
        val descriptionText = "fucking"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
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
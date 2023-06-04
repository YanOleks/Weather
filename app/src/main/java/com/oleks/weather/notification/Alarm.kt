package com.oleks.weather.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.Calendar

class Alarm(
    context: Context
) {
    private val alarmManager: AlarmManager? = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
    private val intent = Intent(context, Notification::class.java)
    private val pendingIntent =
        PendingIntent.getBroadcast(
            context,
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

    fun setAlarm(alarmHour: Int, alarmMinute: Int) {
        val calender = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, alarmHour)
            set(Calendar.MINUTE, alarmMinute)
        }

        alarmManager?.setRepeating(
            AlarmManager.RTC,
            calender.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    fun cancelAlarm() {
        alarmManager?.cancel(pendingIntent)
    }
    init {
        /*val title = "fuck"
        val message = "test"
        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, message)*/
        intent.action = "SetAlarmClock"
    }
}
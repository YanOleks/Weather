package com.oleks.weather.ui.overview

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.oleks.weather.notification.Alarm
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class SettingsView(
    context: Context
) : ViewModel() {
    //region Properties
    private val sharedPreferences = context.getSharedPreferences("Time", Context.MODE_PRIVATE)

    private var _isAlarmOn = mutableStateOf(false)
    private var _alarmHour = mutableStateOf(5)
    private var _alarmMinute = mutableStateOf(0)
    var _withSound by mutableStateOf(true)


    val alarm = Alarm(context)

    var withSound: Boolean
        get() = _withSound
        set(value) {
            _withSound = value
            val editor = sharedPreferences.edit()
            editor.putBoolean("SOUND", value)
            editor.apply()
        }
    var isAlarmOn: MutableState<Boolean>
        get() = _isAlarmOn
        set(value) {
            _isAlarmOn = value

            val editor = sharedPreferences.edit()
            editor.putBoolean("IS_ALARM_ON", value.value)
            editor.apply()
        }

    var alarmHour: MutableState<Int>
        get() = _alarmHour
        set(value) {
            _alarmHour = value

            val editor = sharedPreferences.edit()
            editor.putInt("ALARM_TIME", value.value)
            editor.apply()
        }

    var alarmMinute: MutableState<Int>
        get() = _alarmMinute
        set(value) {
            _alarmMinute = value

            val editor = sharedPreferences.edit()
            editor.putInt("ALARM_TIME", value.value)
            editor.apply()
        }
    //endregion

   fun changeAlarmState(){
       alarm.cancelAlarm()
       val editor = sharedPreferences.edit()
       if(isAlarmOn.value) {
           alarm.setAlarm(alarmHour.value, alarmMinute.value, withSound)

           editor.putBoolean("IS_ALARM_ON", isAlarmOn.value)
           editor.putInt("ALARM_HOUR", alarmHour.value)
           editor.putInt("ALARM_MINUTE", alarmMinute.value)
           editor.apply()
       }else{
           editor.putBoolean("IS_ALARM_ON", isAlarmOn.value)
       }
   }

    init {
        _isAlarmOn.value = sharedPreferences.getBoolean("IS_ALARM_ON", false)
        _alarmHour.value = sharedPreferences.getInt("ALARM_HOUR", 5)
        _alarmMinute.value = sharedPreferences.getInt("ALARM_MINUTE", 0)
        _withSound = sharedPreferences.getBoolean("SOUND", false)
    }
}
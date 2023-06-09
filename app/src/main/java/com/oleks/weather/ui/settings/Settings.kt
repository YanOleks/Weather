package com.oleks.weather.ui.settings

import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.oleks.weather.R
import com.oleks.weather.ui.overview.SettingsView


@Composable
fun SettingsScreen(
    settingsView: SettingsView
) {
    val context = LocalContext.current

    val isAlarmOn = settingsView.isAlarmOn
    val alarmHour = settingsView.alarmHour
    val alarmMinute = settingsView.alarmMinute

    val timePicker = TimePickerDialog(
        context,
        {_, hour: Int, minute: Int->
            alarmHour.value = hour
            alarmMinute.value = minute
            settingsView.changeAlarmState()
        }, alarmHour.value, alarmMinute.value, true
    )

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                stringResource(R.string.notification)
            )
            Switch(checked = isAlarmOn.value, onCheckedChange = {isSwitchOn ->
                isAlarmOn.value = isSwitchOn
                settingsView.changeAlarmState()
            })
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(R.string.notification_time)
            )
            Button(
                onClick = {
                    timePicker.show() }
                ,
                enabled = isAlarmOn.value
            ) {
                Text("${alarmHour.value}:${String.format("%02d",alarmMinute.value)}")
            }
        }
    }
}

@Preview
@Composable
private fun SettingsPreview(){
    val ok = SettingsView(LocalContext.current)
    SettingsScreen(ok)
}
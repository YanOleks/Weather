package com.oleks.weather.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oleks.weather.ui.theme.WeatherTheme

@Composable
fun DayScreen(){
    Column{
        MainPanel()
        Spacer(
            modifier = Modifier
                .height(50.dp)
        )
        HourPanel()
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    WeatherTheme {
        DayScreen()
    }
}
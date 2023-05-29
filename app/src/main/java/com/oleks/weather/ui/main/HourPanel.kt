package com.oleks.weather.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oleks.weather.ui.overview.WeatherView
import com.oleks.weather.ui.theme.WeatherTheme
import androidx.compose.foundation.lazy.items


@Composable
internal fun HourPanel(hourWeather: List<WeatherView.HourWeather>){
    LazyRow (
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        items(hourWeather){hour ->
            HourCard(hour)
        }
    }
}

@Composable
private fun HourCard(hour: WeatherView.HourWeather){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = hour.time,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Divider(
            modifier = Modifier
                .width(40.dp)
        )
        Spacer(
            modifier = Modifier
                .height(3.dp)
        )
        Text("${hour.temperature}°")
        Image(
            painterResource(id = hour.img),
            modifier = Modifier
                .height(50.dp)
                .width(50.dp),
            contentDescription = ""
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    WeatherTheme {
        //HourPanel()
    }
}
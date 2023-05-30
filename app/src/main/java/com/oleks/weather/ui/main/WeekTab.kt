package com.oleks.weather.ui.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oleks.weather.R
import com.oleks.weather.ui.overview.WeatherView

@Composable
fun WeekTab(days: List<WeatherView.Day>){
    LazyColumn(){
        items(days){
            DayCard(day = it)
        }
    }
}

@Composable
fun DayCard(day: WeatherView.Day){
    /*Text(text = day.date)
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = day.img),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "${day.maxTemperature}° / ${day.minTemperature}°",
        )
    }*/

    Card(
        shape = RoundedCornerShape(0),
        modifier = Modifier
            .border(BorderStroke(1.dp, Color.Black))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp, horizontal = 7.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column() {
                Text(text = day.date)
                Text(text = stringResource(id = day.state))
            }
            Row() {
                Image(
                    painter = painterResource(id = day.img),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 7.dp)
                )
                Column() {
                    Text(text = "${day.maxTemperature}°")
                    Text(text = "${day.minTemperature}°")
                }
            }
        }
    }
}

@Preview
@Composable
private fun Test(){
    WeekTab(days = listOf(
        WeatherView.Day(
            "23 May",
            31,
            21,
            R.string.rain,
            R.drawable.raining
        ),
    WeatherView.Day(
        "23 May",
        31,
        21,
        R.string.rain,
        R.drawable.raining
    ),
    WeatherView.Day(
        "23 May",
        31,
        21,
        R.string.rain,
        R.drawable.raining
    )))
}
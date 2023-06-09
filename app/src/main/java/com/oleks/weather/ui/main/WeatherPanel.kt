package com.oleks.weather.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oleks.weather.R
import com.oleks.weather.ui.overview.WeatherView

@Composable
internal fun MainPanel(currentWeather: WeatherView.CurrentWeather){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 5.dp,
                end = 5.dp
            )
    ) {
        Row{
<<<<<<< HEAD
            Text("↓${currentWeather.lowest}°")
=======
            Text("↓${8}°")
>>>>>>> master
            Spacer(
                modifier = Modifier
                    .width(5.dp)
            )
<<<<<<< HEAD
            Text("↑${currentWeather.highest}°")
=======
            Text("↑${8}°")
>>>>>>> master
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
<<<<<<< HEAD
                "${currentWeather.temperature}°",
=======
                "${8}°",
>>>>>>> master
                textAlign = TextAlign.Left,
                fontSize = 48.sp,
                modifier = Modifier
                    .weight(2f)
            )
            Image(
                painterResource(currentWeather.img),
                modifier = Modifier
                    .weight(1f)
                    .height(80.dp),
                contentDescription = ""
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
<<<<<<< HEAD
                "${stringResource(R.string.feels)} ${currentWeather.apparent}°",
=======
                //TODO: add R.string
                "Feels like ${8}",
>>>>>>> master
                modifier = Modifier
                    .weight(2f)
            )
            Text(
                stringResource(id = currentWeather.state),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}
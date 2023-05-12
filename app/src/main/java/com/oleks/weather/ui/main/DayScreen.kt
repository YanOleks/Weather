package com.oleks.weather.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oleks.weather.R
import com.oleks.weather.ui.overview.WeatherView
import com.oleks.weather.ui.theme.WeatherTheme

@Composable
fun DayScreen(viewModel: WeatherView){

    val currentWeather = viewModel.currentWeather.observeAsState()
    val hourWeather = viewModel.hourWeather.observeAsState()
    val loaded = viewModel.loading.observeAsState(initial = false)

    LaunchedEffect(key1 = Unit){
        viewModel.getWeather()
    }

    if (!loaded.value){
        Column{
            MainPanel(currentWeather.value!!)
            Spacer(
                modifier = Modifier
                    .height(50.dp)
            )
            HourPanel(hourWeather.value!!)
        }
    } else {
        Box(modifier = Modifier
            .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ){
            Text(
                stringResource(R.string.loading),
                modifier = Modifier,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    WeatherTheme {
        //DayScreen()
    }
}
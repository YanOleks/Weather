package com.oleks.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.oleks.weather.data.di.WeatherRepo
import com.oleks.weather.ui.main.DayScreen
import com.oleks.weather.ui.overview.WeatherView
import com.oleks.weather.ui.overview.WeatherViewFactory
import com.oleks.weather.ui.theme.WeatherTheme

class MainActivity : ComponentActivity() {
    lateinit var viewModel: WeatherView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this,
            WeatherViewFactory(WeatherRepo()))[WeatherView::class.java]
        //Log.i("TEST", viewModel.weather.value!!.currentWeather.temperature.toString())
        setContent {
            WeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DayScreen(viewModel)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherTheme {
        //DayScreen()

    }
}
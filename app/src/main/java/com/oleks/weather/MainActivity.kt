package com.oleks.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.oleks.weather.data.api.apiHelper
import com.oleks.weather.ui.main.DayScreen
import com.oleks.weather.ui.overview.WeatherViewModel
import com.oleks.weather.ui.theme.WeatherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var weatherViewModel: WeatherViewModel
        super.onCreate(savedInstanceState)
        setContent {
            WeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    weatherViewModel = WeatherViewModel(apiHelper)
                    DayScreen()

                }
            }
        }
    }
}
@Composable
fun Text(){
    Text("LOL")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherTheme {
        //DayScreen()
    }
}
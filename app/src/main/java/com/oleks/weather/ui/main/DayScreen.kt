package com.oleks.weather.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.oleks.weather.R
import com.oleks.weather.notification.Notification
import com.oleks.weather.ui.overview.WeatherView
import com.oleks.weather.ui.theme.WeatherTheme

@Composable
<<<<<<< HEAD
fun DayScreen(viewModel: WeatherView, nav: NavController){

    var isButtonClicked by remember { mutableStateOf(false) }
    val currentWeather = viewModel.currentWeather.observeAsState()
    val hourWeather = viewModel.hourWeather.observeAsState()
    val weekData = viewModel.weekWeather.observeAsState()
    val loaded = viewModel.loading.observeAsState(initial = false)
    var placeName by remember {
        mutableStateOf("")
    }



    var isPlaceChosen by remember {
        mutableStateOf(viewModel.isPlaceChosen)
    }
    placeName = if (!isPlaceChosen){
        stringResource(id = R.string.position)
    } else {
        viewModel.place
    }

    LaunchedEffect(key1 = Unit){
        viewModel.getWeather()
    }
    if (!loaded.value){

        Column{
            Button(onClick = {
                    nav.navigate("screen2")
                },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(0),
                colors = ButtonDefaults.textButtonColors()
            ) {

                Row {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "" )
                }
                Text(
                    text = placeName,
                    style = TextStyle(textDecoration = TextDecoration.Underline, ),
                )
            }

            var state by remember { mutableStateOf(0) }
            val titles = listOf(stringResource(id = R.string.today), stringResource(id = R.string.week))

            TabRow(selectedTabIndex = state) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = state == index,
                        onClick = { state = index }
                    )
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            if(state == 0){
                MainPanel(currentWeather.value!!)
                Spacer(
                    modifier = Modifier
                        .height(50.dp)
                )
                HourPanel(hourWeather.value!!)
                Column() {
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(stringResource(id = R.string.humidity) + currentWeather.value!!.humidity + "%")
                    Text(stringResource(id = R.string.wind) + currentWeather.value!!.wind + stringResource(
                        id = R.string.speed
                    ))
                }
            } else if (state == 1){
                WeekTab(weekData.value!!)
            }
        }
    } else {
        Box(
=======
fun DayScreen(){
    Column{
        //TODO: change to class "PANEL"
        MainPanel()
        Spacer(
>>>>>>> master
            modifier = Modifier
                .fillMaxWidth()
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
        //DayScreen(WeatherView(WeatherRepo()))
    }
}
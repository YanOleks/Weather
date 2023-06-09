package com.oleks.weather.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.oleks.weather.data.di.WeatherRepo
import com.oleks.weather.data.geonames.PlacesRepo
import com.oleks.weather.ui.main.DayScreen
import com.oleks.weather.ui.main.Search
import com.oleks.weather.ui.overview.PlaceView
import com.oleks.weather.ui.overview.SettingsView
import com.oleks.weather.ui.overview.WeatherView
import com.oleks.weather.ui.settings.SettingsScreen

@Composable
fun MyApp(navController: NavController, weatherView: WeatherView, settingsView: SettingsView, placeView: PlaceView) {
    /*val navController = rememberNavController()
    val weatherView = WeatherView(WeatherRepo())
    val settingsView = SettingsView(LocalContext.current)
    val placeView = PlaceView(PlacesRepo())*/
    /*NavHost(navController, startDestination = "home") {
        composable("home") {
            DayScreen(weatherView, navController)
        }
        composable("screen2") {
            Search(placeView, navController)
        }
        composable("settings"){
            SettingsScreen(settingsView)
        }
    }*/
    navController.addOnDestinationChangedListener{ _, destination, _ ->
        if (destination.route == "home"){
            if(placeView.place != null && placeView.choosen) {
                placeView.choosen = false
                weatherView.loading.value = false
                weatherView.latitude = placeView.place!!.lat.toDouble()
                weatherView.longitude = placeView.place!!.lng.toDouble()
                weatherView.place = "${placeView.place!!.name}, ${placeView.place!!.adminName1}, ${placeView.place!!.countryName}"
                weatherView.isPlaceChosen = true
                weatherView.getWeather()
            }
        }
    }
}
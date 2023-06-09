package com.oleks.weather

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.oleks.weather.data.di.WeatherRepo
import com.oleks.weather.data.geonames.PlacesRepo
import com.oleks.weather.notification.channelID
import com.oleks.weather.ui.main.About
import com.oleks.weather.ui.main.DayScreen
import com.oleks.weather.ui.main.Search
import com.oleks.weather.ui.menu.AppBar
import com.oleks.weather.ui.menu.Drawer
import com.oleks.weather.ui.menu.MenuItems
import com.oleks.weather.ui.overview.PlaceView
import com.oleks.weather.ui.overview.SettingsView
import com.oleks.weather.ui.overview.WeatherView
import com.oleks.weather.ui.settings.SettingsScreen
import com.oleks.weather.ui.theme.WeatherTheme
import kotlinx.coroutines.launch

var LATITUDE:Double = 50.44
var LONGITUDE:Double =30.51
class MainActivity : ComponentActivity() {
    private lateinit var locationManager: LocationManager


    val REQUEST_LOCATION_PERMISSION = 1
    private val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            // Handle the location change here
            LATITUDE = location.latitude
            LONGITUDE = location.longitude
            // Use the latitude and longitude variables to access the user's location
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
            // Handle the location provider status change here
        }

        override fun onProviderEnabled(provider: String) {
            // Handle the location provider enabled here
        }

        override fun onProviderDisabled(provider: String) {
            // Handle the location provider disabled here
        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel()
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Register for location updates
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
        } else {
            // Request location permission from the user
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_PERMISSION)
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
        val lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        if (lastKnownLocation != null) {
            LATITUDE = lastKnownLocation.latitude
            LONGITUDE = lastKnownLocation.longitude
            // Use the latitude and longitude values as needed
        }

        setContent {
            WeatherTheme {
                val navController = rememberNavController()
                val weatherView = WeatherView(WeatherRepo())
                val settingsView = SettingsView(LocalContext.current)
                val placeView = PlaceView(PlacesRepo())
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

                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        Drawer(
                            items = MenuItems.items,
                            onItemClick = {
                                navController.navigate(it.id)
                            }
                        )
                    },
                ) {
                    Scaffold(
                        topBar = {
                            AppBar(
                                onNavigationIconClick = {
                                    scope.launch {
                                        drawerState.apply {
                                            if(isClosed) open() else close()
                                        }
                                    }
                                }
                            )
                        }
                    ){
                        Box(modifier = Modifier.padding(it)){
//                            MyApp(navController, weatherView, settingsView, placeView)
                            //DayScreen(viewModel = weatherView, nav = navController)
                            NavHost(navController, startDestination = "home") {
                                composable("home") {
                                    DayScreen(weatherView, navController)
                                }
                                composable("screen2") {
                                    Search(placeView, navController)
                                }
                                composable("settings"){
                                    SettingsScreen(settingsView)
                                }
                                composable("about"){
                                    About()
                                }
                            }
                        }
                        /*Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {

                        }*/
                    }
                }
                // A surface container using the 'background' color from the theme

            }
        }



    }

    private fun createNotificationChannel() {
        val name = "Notif Channel"
        val desc = "A Description of the Channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name , importance)
        channel.description = desc
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherTheme {
        //DayScreen()

    }
}
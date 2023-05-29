package com.oleks.weather

import android.Manifest
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.oleks.weather.ui.MyApp
import com.oleks.weather.ui.menu.AppBar
import com.oleks.weather.ui.menu.Drawer
import com.oleks.weather.ui.menu.MenuItems
import com.oleks.weather.ui.theme.WeatherTheme
import kotlinx.coroutines.launch

var LATITUDE:Double = 0.0
var LONGITUDE:Double = 0.0
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
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        Drawer(
                            items = MenuItems.items,
                            onItemClick = {
                                println("Fuck")
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
                            MyApp()
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
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherTheme {
        //DayScreen()

    }
}
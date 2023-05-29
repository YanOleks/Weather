package com.oleks.weather.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.oleks.weather.data.geonames.model.Geoname
import com.oleks.weather.ui.overview.PlaceView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(model: PlaceView, nav: NavController) {


    var search by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var places = model.places

    LaunchedEffect(model.places){
        places = model.places
    }

    Column() {
        Row{
            Button(onClick = {nav.navigate("home")}) {
                Text(text = "Back")
            }
            TextField(
                value = search,
                onValueChange = {
                    search = it
                    model.debouncedSearch(it.text)
                    //Log.i("TEST", model.loading.toString())
                },
                label = { Text("test") }
            )
        }
        if (places == null || places.isEmpty()){
            //TODO: current place
            Text("Not found")
        } else {
            LazyColumn {
                items(places){
                    Place(it, onPlaceClicked = {chosen ->
                        model.place = chosen
                        model.choosen = true
                        nav.navigate("home")
                    })
                }
            }
        }
    }
}

@Composable
private fun Place(place: Geoname, onPlaceClicked: (Geoname) -> Unit){
    TextButton(onClick = { onPlaceClicked(place) }) {
        Text("${place.name}, ${place.adminName1}, ${place.countryName}")
    }
}

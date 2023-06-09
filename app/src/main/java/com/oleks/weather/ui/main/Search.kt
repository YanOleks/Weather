package com.oleks.weather.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.oleks.weather.R
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
            TextButton(
                onClick = {nav.navigate("home")},

            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
            TextField(
                value = search,
                onValueChange = {
                    search = it
                    model.debouncedSearch(it.text)
                    //Log.i("TEST", model.loading.toString())
                },
                label = {
                    Text(stringResource(id = R.string.search))
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "")
                },
                modifier = Modifier
                    .padding(start = 3.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent
                )
            )

        }
        Spacer(modifier = Modifier.height(25.dp))
        if (search.text == ""){
            TextButton(
                onClick = {nav.navigate("home")},
                Modifier.padding(start = 15.dp)
            ) {
                Text(stringResource(id = R.string.position))
            }
        } else if (places == null || places.isEmpty()){
            //TODO: current place
            Text(stringResource(
                id = R.string.noFound),
                Modifier.padding(start = 15.dp)
            )
        } else {
            LazyColumn(
                Modifier.padding(start = 15.dp)
            ) {
                items(places){
                    Place(it, onPlaceClicked = {chosen ->
                        model.place = chosen
                        model.choosen = true
                        model.places = emptyList()
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

package com.oleks.weather.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun About(){
    val openmeteo = buildAnnotatedString {
        val str = "open-meteo"
        append(str)
        addStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            ), start = 0, end = str.length - 1
        )
        addStringAnnotation(
            tag = "URL",
            annotation = "https://open-meteo.com/",
            start = 0,
            end = str.length - 1
        )
    }
    val geonames = buildAnnotatedString {
        val str = "geonames"
        append(str)
        addStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            ), start = 0, end = str.length - 1
        )
        addStringAnnotation(
            tag = "URL",
            annotation = "https://www.geonames.org/",
            start = 0,
            end = str.length - 1
        )
    }
    val list = listOf(
        "Good Ware - Flaticon",
        "kosonicon - Flaticon",
        "Andy Horvath - Flaticon",
        "rawpixel.com on Freepik",

    )
    Column() {
        Text("Weather data: $openmeteo")
        Text("Geodata: $geonames")
        Text("Authors of images: ")
        LazyColumn{
            items(list){
                Row{
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = it)
                }
            }
        }
    }
}

@Preview
@Composable
private fun Test(){
    About()
}
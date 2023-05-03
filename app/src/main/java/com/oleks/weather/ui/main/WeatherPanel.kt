package com.oleks.weather.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oleks.weather.R

@Composable
internal fun MainPanel(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 5.dp,
                end = 5.dp
            )
    ) {
        Row{
            Text("↓10°")
            Spacer(
                modifier = Modifier
                    .width(5.dp)
            )
            Text("↑20°")
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                "15°",
                textAlign = TextAlign.Left,
                fontSize = 48.sp,
                modifier = Modifier
                    .weight(2f)
            )
            Image(
                painterResource(id = R.drawable.ic_test),
                modifier = Modifier
                    .weight(1f)
                    .height(80.dp),
                contentDescription = ""
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Text(
                "Feels like 13°",
                modifier = Modifier
                    .weight(2f)
            )
            Text(
                "Test state",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}
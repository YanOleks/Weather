package com.oleks.weather.ui.menu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import com.oleks.weather.R

object MenuItems {
    val items = listOf(
        MenuItem(
            id = "home",
            title = R.string.home,
            icon = Icons.Default.Home
        ),
        MenuItem(
            id = "settings",
            title = R.string.settings,
            icon = Icons.Default.Settings
        ),
        MenuItem(
            id = "about",
            title = R.string.about,
            icon = Icons.Default.Info
        )
    )
}
package com.oleks.weather.ui.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oleks.weather.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Drawer(
    items: List<MenuItem>,
    onItemClick: (MenuItem) -> Unit
){
    ModalDrawerSheet() {
        LazyColumn(){
            items(items) {item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onItemClick(item)
                        }
                        .padding(16.dp)
                ){
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = stringResource(id = item.title),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    onNavigationIconClick: () -> Unit
){
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "menu"
                )
                
            }
        }
    )
}

@Preview
@Composable
private fun Test(){
    /*Drawer(items = listOf(
        MenuItem(
            id = "home",
            title = "Home",
            icon = Icons.Default.Home
        ),
        MenuItem(
            id = "settings",
            title = "Settings",
            icon = Icons.Default.Settings
        ),
        MenuItem(
            id = "about",
            title = "About",
            icon = Icons.Default.Info
        )
    ), onItemClick = {
        println("Fuck")
    })*/
}

data class MenuItem(
    val id: String,
    val title: Int,
    val contentDescription: String = "",
    val icon: ImageVector
)
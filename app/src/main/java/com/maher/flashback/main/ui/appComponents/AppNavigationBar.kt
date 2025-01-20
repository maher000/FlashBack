package com.maher.flashback.main.ui.appComponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.maher.flashback.core.designSystem.components.FlashBackNavigationBar
import com.maher.flashback.core.designSystem.components.FlashBackNavigationBarItem
import com.maher.flashback.core.navigation.Screen

@Composable
fun AppNavigationBar(
    startDestination: Screen = Screen.Feed,
    navigateToDestination: (Screen) -> Unit
) {
    var currentDestination by rememberSaveable { mutableStateOf(startDestination.route) }

    val destinations = listOf(Screen.Feed, Screen.Camera, Screen.Profile)
    val selectedIcons = listOf(Icons.Rounded.Home, Icons.Rounded.Share, Icons.Rounded.Person)
    val icons = listOf(Icons.Outlined.Home, Icons.Outlined.Share, Icons.Outlined.Person)
    FlashBackNavigationBar {
        destinations.forEachIndexed { index, destination ->
            FlashBackNavigationBarItem(
                icon = {
                    Icon(
                        imageVector = icons[index],
                        contentDescription = destination.route,
                    )
                },
                selectedIcon = {
                    Icon(
                        imageVector = selectedIcons[index],
                        contentDescription = destination.route,
                        )
                },
                alwaysShowLabel = false,
                selected = currentDestination == destination.route,
                onClick = {
                    currentDestination = destination.route
                    navigateToDestination(destination)
                }
            )
        }
    }
}
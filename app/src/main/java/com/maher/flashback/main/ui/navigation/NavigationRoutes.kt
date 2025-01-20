package com.maher.flashback.main.ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import com.maher.flashback.core.navigation.Screen

fun navigateToMainDestinations(screen: Screen, navController: NavHostController) {
    val navOptions = navOptions {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }
    when (screen) {
        Screen.Feed -> navController.navigate(Screen.Feed.route, navOptions)
        Screen.Profile -> navController.navigate(Screen.Profile.route, navOptions)
        Screen.Camera -> navController.navigate(Screen.Camera.route)
        else -> Unit
    }
}
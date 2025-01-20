package com.maher.flashback.main.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.maher.flashback.core.navigation.Screen
import com.maher.flashback.features.camera.CameraScreenRoute
import com.maher.flashback.features.feed.presentatation.FeedScreenRoute
import com.maher.flashback.features.post.add.presentation.AddPostScreenRoute
import com.maher.flashback.features.profile.presetation.ProfileScreenRoute

@Composable
fun FlashBackNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Screen.Feed.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.Feed.route) {
            FeedScreenRoute()
        }
        composable(Screen.Camera.route) {
            CameraScreenRoute(navController)
        }
        composable<Screen.AddPost> {
            AddPostScreenRoute(navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreenRoute()
        }
    }
}
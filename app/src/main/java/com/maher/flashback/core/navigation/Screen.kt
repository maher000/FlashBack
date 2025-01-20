package com.maher.flashback.core.navigation

import kotlinx.serialization.Serializable

enum class ScreenName {
    FEED,
    CAMERA,
    PROFILE,
    ADD_POST
}

@Serializable
sealed class Screen(val route: String) {
    data object Feed : Screen(ScreenName.FEED.name)
    data object Camera : Screen(ScreenName.CAMERA.name)
    data object Profile : Screen(ScreenName.PROFILE.name)
    @Serializable
    data class AddPost(val imageUri: String) : Screen(ScreenName.ADD_POST.name)
}
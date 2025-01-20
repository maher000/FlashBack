package com.maher.flashback.features.profile.presetation

import com.maher.flashback.features.feed.presentatation.PostUiModel

sealed interface ProfileUiState {
    data class Success(val posts: List<PostUiModel>) : ProfileUiState
    data object Error : ProfileUiState
    data object Loading : ProfileUiState
}

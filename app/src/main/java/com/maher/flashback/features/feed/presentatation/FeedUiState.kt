package com.maher.flashback.features.feed.presentatation

sealed interface FeedUiState {
    data class Success(val posts: List<PostUiModel>) : FeedUiState
    data object Error : FeedUiState
    data object Loading : FeedUiState
}

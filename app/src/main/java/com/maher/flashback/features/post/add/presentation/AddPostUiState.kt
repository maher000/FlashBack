package com.maher.flashback.features.post.add.presentation

sealed interface AddPostUiState {
    data object Idle : AddPostUiState
    data object Success : AddPostUiState
    data object Error : AddPostUiState
    data object Loading : AddPostUiState
}

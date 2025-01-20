package com.maher.flashback.features.post.add.presentation

import androidx.core.net.toUri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.maher.flashback.core.navigation.Screen
import com.maher.flashback.features.feed.domain.PostsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddPostViewModel(
    savedStateHandle: SavedStateHandle,
    private val postRepository: PostsRepository
) : ViewModel() {

    val imageUri = savedStateHandle.toRoute<Screen.AddPost>().imageUri.toUri()


    private val _uiState = MutableStateFlow<AddPostUiState>(AddPostUiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun addPost(description: String) {
        viewModelScope.launch {
            _uiState.value = AddPostUiState.Loading
            val result = postRepository.addPost(description, imageUri)
            if (result) {
                _uiState.value = AddPostUiState.Success
            } else {
                _uiState.value = AddPostUiState.Error
            }
        }
    }

}
package com.maher.flashback.features.profile.presetation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maher.flashback.core.data.Authentication
import com.maher.flashback.features.feed.domain.PostsRepository
import com.maher.flashback.features.feed.presentatation.toPostUiModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class ProfileViewModel(postsRepository: PostsRepository) : ViewModel() {

    val currentUser = Authentication.CurrentUser

    val posts: StateFlow<ProfileUiState> = postsRepository.getUserPosts()
        .map { posts ->
            posts.map { post ->
                post.toPostUiModel()
            }
        }
        .map(ProfileUiState::Success)
        .onStart<ProfileUiState> { emit(ProfileUiState.Loading) }
        .catch { emit(ProfileUiState.Error) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ProfileUiState.Loading,
        )
}
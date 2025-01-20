package com.maher.flashback.features.feed.presentatation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maher.flashback.features.feed.domain.PostsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FeedsViewModel(
    private val postRepository: PostsRepository
) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val posts: StateFlow<FeedUiState> = postRepository.likedPosts
        .flatMapLatest { likedPosts ->
            postRepository.getFeedPosts()
                .map { posts ->
                    posts.map { post ->
                        val postUiModel = post.toPostUiModel()
                        val isLiked = likedPosts.any { it.postId == postUiModel.id }
                        postUiModel.copy(isLiked = isLiked)
                    }
                }
        }
        .map(FeedUiState::Success)
        .onStart<FeedUiState> { emit(FeedUiState.Loading) }
        .catch { emit(FeedUiState.Error) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = FeedUiState.Loading,
        )

    fun likePost(postId: String, isLiked: Boolean) {
        viewModelScope.launch {
            postRepository.likePost(postId, isLiked)
        }
    }
}

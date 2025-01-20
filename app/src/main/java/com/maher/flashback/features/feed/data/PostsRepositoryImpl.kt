package com.maher.flashback.features.feed.data

import android.net.Uri
import com.maher.flashback.core.coroutines.DispatcherProvider
import com.maher.flashback.core.data.Authentication
import com.maher.flashback.core.data.model.toPostDomain
import com.maher.flashback.core.data.network.api.PostApi
import com.maher.flashback.features.feed.domain.PostsRepository
import com.maher.flashback.features.feed.domain.model.Post
import com.maher.flashback.features.feed.presentatation.LikedPost
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class PostsRepositoryImpl(
    private val postApi: PostApi,
    private val dispatcher: CoroutineDispatcher = DispatcherProvider.io
) : PostsRepository {

    override val likedPosts = MutableStateFlow<Set<LikedPost>>(emptySet())

    override fun getFeedPosts(): Flow<List<Post>> = flow {
        try {
            val posts= postApi.getFeedPosts().map { it.toPostDomain() }
            emit(posts)
        }catch (e: Exception) {
            // For real world scenario emit an error resource
            emit(emptyList())
        }
    }.flowOn(dispatcher)

    override fun getUserPosts(): Flow<List<Post>> = flow {
        try {
            val posts= postApi.getUserPosts(Authentication.CurrentUser.id).map { it.toPostDomain() }
            emit(posts)
        }catch (e: Exception) {
            // For real world scenario emit an error resource
            emit(emptyList())
        }
    }

    override suspend fun addPost(description: String, imageUri: Uri): Boolean {
        return withContext(dispatcher) {
            // Simulate an api call to add post
            delay(2000L)
            true
        }
    }

    override suspend fun likePost(postId: String, isLiked: Boolean) {
        withContext(dispatcher) {
            // simulate an api call to like post
            if (!isLiked) {
                likedPosts.value.firstOrNull { it.postId == postId }?.let {
                    likedPosts.value = likedPosts.value.minus(it)
                }

            } else {
                likedPosts.value = likedPosts.value.plus(LikedPost(postId, isLiked))
            }
        }
    }
}
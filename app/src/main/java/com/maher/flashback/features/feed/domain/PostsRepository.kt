package com.maher.flashback.features.feed.domain

import android.net.Uri
import com.maher.flashback.features.feed.domain.model.Post
import com.maher.flashback.features.feed.presentatation.LikedPost
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    val likedPosts: Flow<Set<LikedPost>>

    fun getFeedPosts(): Flow<List<Post>>

    fun getUserPosts(): Flow<List<Post>>

    suspend fun addPost(description: String, imageUri: Uri): Boolean

    suspend fun likePost(postId: String, isLiked: Boolean)

}
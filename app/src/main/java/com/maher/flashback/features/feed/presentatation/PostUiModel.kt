package com.maher.flashback.features.feed.presentatation

import com.maher.flashback.features.feed.domain.model.Post

data class PostUiModel(
    val id: String,
    val userId: String,
    val createdAt: String,
    val imageUrl: String,
    val description: String,
    val isLiked: Boolean = false
)

fun Post.toPostUiModel() = PostUiModel(
    id = id,
    userId = userId,
    createdAt = createdAt,
    imageUrl = imageUrl,
    description = description
)

data class LikedPost(
    val postId: String = "",
    val isLiked: Boolean = false
)
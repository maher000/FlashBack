package com.maher.flashback.core.data.model

import com.maher.flashback.features.feed.domain.model.Post
import kotlinx.serialization.Serializable

@Serializable
data class PostEntity(
    val id: String,
    val userId: String,
    val createdAt: String,
    val imageUrl: String,
    val description: String
)

fun PostEntity.toPostDomain() = Post(
    id = id,
    userId = userId,
    createdAt = createdAt,
    imageUrl = imageUrl,
    description = description
)

package com.maher.flashback.features.feed.domain.model

data class Post(
    val id: String,
    val userId: String,
    val createdAt: String,
    val imageUrl: String,
    val description: String,
)

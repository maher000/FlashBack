package com.maher.flashback.core.data.model

data class User(
    val id: String,
    val name: String,
    val bio: String = "",
    val avatar: String,
)

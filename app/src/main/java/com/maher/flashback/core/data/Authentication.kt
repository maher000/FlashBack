package com.maher.flashback.core.data

import com.maher.flashback.core.data.model.User

/**
 * An object to simulate user authentication
 */
object Authentication{
    val CurrentUser = User(
        id = "1",
        name = "Maher Gafsi",
        bio = "I love to travel and explore new places, especially in the desert. Follow my adventure all around the world",
        avatar = "https://images.unsplash.com/photo-1736926442592-8ca433aa1e19?q=80&w=1886&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
    )
}
package com.maher.flashback.core.data.network.api

import com.maher.flashback.core.data.model.PostEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApi {

    @GET(value = "2/posts/")
    suspend fun getFeedPosts(): List<PostEntity>

    @GET(value = "{userId}/posts/")
    suspend fun getUserPosts(@Path("userId") userId: String): List<PostEntity>
}
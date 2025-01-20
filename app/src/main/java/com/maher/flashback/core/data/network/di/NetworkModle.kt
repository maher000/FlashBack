package com.maher.flashback.core.data.network.di

import com.maher.flashback.core.data.network.api.PostApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL =
    "https://678a2d80dd587da7ac290c19.mockapi.io/users/"
private const val OK_HTTP_TIMEOUT = 60L

val networkModule = module {
    single<PostApi> {
        get<Retrofit>().create(PostApi::class.java)
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(get<Json>().asConverterFactory("application/json".toMediaType()))
            .client(get())
            .build()
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .connectTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    single<Json> {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }
}
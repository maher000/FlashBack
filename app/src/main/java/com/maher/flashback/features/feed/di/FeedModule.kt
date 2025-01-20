package com.maher.flashback.features.feed.di

import com.maher.flashback.features.feed.data.PostsRepositoryImpl
import com.maher.flashback.features.feed.domain.PostsRepository
import com.maher.flashback.features.feed.presentatation.FeedsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val feedModule = module {
    viewModel {
        FeedsViewModel(postRepository = get())
    }

    single<PostsRepository> { PostsRepositoryImpl(get()) }

}
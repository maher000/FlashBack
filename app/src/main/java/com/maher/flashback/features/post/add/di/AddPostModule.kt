package com.maher.flashback.features.post.add.di

import com.maher.flashback.features.post.add.presentation.AddPostViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val addPostModule = module {
    viewModel {
        AddPostViewModel(savedStateHandle = get(), postRepository = get())
    }
    }
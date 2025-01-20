package com.maher.flashback.features.profile.di

import com.maher.flashback.features.profile.presetation.ProfileViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val profileModule = module {
    viewModel { ProfileViewModel(postsRepository = get()) }
}
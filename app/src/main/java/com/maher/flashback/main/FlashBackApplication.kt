package com.maher.flashback.main

import android.app.Application
import com.maher.flashback.core.data.network.di.networkModule
import com.maher.flashback.features.feed.di.feedModule
import com.maher.flashback.features.post.add.di.addPostModule
import com.maher.flashback.features.profile.di.profileModule
import com.maher.flashback.main.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androix.startup.KoinStartup
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.KoinConfiguration

@OptIn(KoinExperimentalAPI::class)
class FlashBackApplication : Application(), KoinStartup {

    override fun onKoinStartup(): KoinConfiguration =
        KoinConfiguration {
            androidLogger()
            androidContext(this@FlashBackApplication)
            modules(
                appModule,
                networkModule,
                feedModule,
                addPostModule,
                profileModule
            )
        }
}
package com.testemobile.githubjava.Application

import android.app.Application
import com.testemobile.githubjava.module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GithubRepoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {
            androidContext(this@GithubRepoApplication)
            modules(appModule)
        }
    }
}

package com.nimesh.vasani.speer_technologies_android

import android.app.Application
import com.nimesh.vasani.speer_technologies_android.data.di.provideAppModule
import com.nimesh.vasani.speer_technologies_android.data.di.providesNetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(listOf(provideAppModule(), providesNetworkModule()))
        }
    }
}
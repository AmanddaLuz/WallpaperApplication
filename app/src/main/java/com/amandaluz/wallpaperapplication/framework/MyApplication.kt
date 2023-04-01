package com.amandaluz.wallpaperapplication.framework

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication: Application(), Configuration.Provider{

    @Inject
    lateinit var workerFactory : HiltWorkerFactory
    override fun getWorkManagerConfiguration() : Configuration {
        return  Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .setWorkerFactory(workerFactory)
            .build()
    }
}
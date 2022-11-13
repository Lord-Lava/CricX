package com.lava

import android.app.Application
import com.lava.cricx.BuildConfig
import com.lava.cricx.data.local.SharedPrefs
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        super.onCreate()

        SharedPrefs.init(applicationContext)
        if(BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}
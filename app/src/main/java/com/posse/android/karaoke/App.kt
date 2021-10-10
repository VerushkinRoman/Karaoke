package com.posse.android.karaoke

import android.app.Application
import com.posse.android.karaoke.di.modules.AppComponent
import com.posse.android.karaoke.di.modules.AppModule
import com.posse.android.karaoke.di.modules.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {

        lateinit var instance: App
    }
}
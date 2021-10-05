package com.posse.android.karaoke

import android.app.Application
import com.posse.android.karaoke.di.components.AppComponent
import com.posse.android.karaoke.di.components.DaggerAppComponent
import com.posse.android.karaoke.di.components.SongSubcomponent
import com.posse.android.karaoke.di.components.SongsSubcomponent
import com.posse.android.karaoke.di.modules.AppModule

class App : Application() {

    lateinit var appComponent: AppComponent

    var songsSubcomponent: SongsSubcomponent? = null
        private set

    var songSubcomponent: SongSubcomponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initSongsSubcomponent() = appComponent.songsSubcomponent().also {
        songsSubcomponent = it
    }

    fun initSongSubcomponent() = appComponent.songsSubcomponent().songSubcomponent().also {
        songSubcomponent = it
    }

    fun releaseSongsScope() {
        songsSubcomponent = null
    }

    fun releaseSongScope() {
        songSubcomponent = null
    }

    companion object {

        lateinit var instance: App
    }
}
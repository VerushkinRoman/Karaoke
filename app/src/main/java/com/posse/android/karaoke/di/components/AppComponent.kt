package com.posse.android.karaoke.di.components

import com.posse.android.karaoke.activity.MainActivity
import com.posse.android.karaoke.activity.MainPresenter
import com.posse.android.karaoke.di.modules.*
import com.posse.android.karaoke.screens.songDetails.SongDetailsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        ImageModule::class,
        FilesystemModule::class
    ]
)
interface AppComponent {

    fun songsSubcomponent(): SongsSubcomponent

    fun presenter(): MainPresenter

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(songDetailsFragment: SongDetailsFragment)
}
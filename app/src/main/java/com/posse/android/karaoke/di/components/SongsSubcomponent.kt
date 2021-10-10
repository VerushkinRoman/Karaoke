package com.posse.android.karaoke.di.components

import com.posse.android.karaoke.di.modules.PresenterListenerModule
import com.posse.android.karaoke.di.modules.SongsModule
import com.posse.android.karaoke.di.scopes.SongsScope
import com.posse.android.karaoke.screens.songs.SongsPresenter
import dagger.Subcomponent

@SongsScope
@Subcomponent(
    modules = [
        SongsModule::class,
        PresenterListenerModule::class
    ]
)
interface SongsSubcomponent {

    fun songSubcomponent(): SongSubcomponent
    fun inject(songsPresenter: SongsPresenter)
}
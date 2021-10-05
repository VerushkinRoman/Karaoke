package com.posse.android.karaoke.di.components

import com.posse.android.karaoke.di.modules.SongModule
import com.posse.android.karaoke.di.scopes.SongScope
import com.posse.android.karaoke.screens.songDetails.SongDetailsPresenter
import dagger.Subcomponent

@SongScope
@Subcomponent(modules = [SongModule::class])
interface SongSubcomponent {

    fun inject(songPresenter: SongDetailsPresenter)
}
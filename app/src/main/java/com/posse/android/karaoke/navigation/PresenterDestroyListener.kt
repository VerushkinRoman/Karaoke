package com.posse.android.karaoke.navigation

import com.posse.android.karaoke.App
import com.posse.android.karaoke.screens.songDetails.SongDetailsPresenter
import com.posse.android.karaoke.screens.songs.SongsPresenter
import moxy.MvpPresenter
import moxy.MvpView

class PresenterDestroyListener {

    fun <T : MvpView> releaseScope(presenter: MvpPresenter<T>) {
        when (presenter) {
            is SongsPresenter -> { App.instance.releaseSongsScope() }
            is SongDetailsPresenter -> { App.instance.releaseSongScope() }
        }
    }
}
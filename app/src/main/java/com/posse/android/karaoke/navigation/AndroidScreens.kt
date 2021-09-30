package com.posse.android.karaoke.navigation

import com.posse.android.karaoke.model.Song
import com.posse.android.karaoke.screens.songDetails.SongDetailsFragment
import com.posse.android.karaoke.screens.songs.SongsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object AndroidScreens {

    class SongsScreen : SupportAppScreen() {

        override fun getFragment() = SongsFragment()
    }

    class SongDetailsScreen(private val song: Song) : SupportAppScreen() {

        override fun getFragment() = SongDetailsFragment.newInstance(song)
    }
}
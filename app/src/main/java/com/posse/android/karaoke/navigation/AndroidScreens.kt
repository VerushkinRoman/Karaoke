package com.posse.android.karaoke.navigation

import com.posse.android.karaoke.model.TopSongs
import com.posse.android.karaoke.screens.songDetails.SongDetailsFragment
import com.posse.android.karaoke.screens.songs.SongsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object AndroidScreens {

    class SongsScreen : SupportAppScreen() {

        override fun getFragment() = SongsFragment()
    }

    class SongDetailsScreen(private val song: TopSongs.Tracks.Song) : SupportAppScreen() {

        override fun getFragment() = SongDetailsFragment.newInstance(song)
    }
}
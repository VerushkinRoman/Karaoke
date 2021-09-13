package com.posse.android.karaoke.screens

import com.posse.android.karaoke.view.ui.SongsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object AndroidScreens {

    class SongsScreen : SupportAppScreen() {

        override fun getFragment() = SongsFragment()
    }
}
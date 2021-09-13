package com.posse.android.karaoke.screens

import com.posse.android.karaoke.view.ui.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object AndroidScreens {

    class UsersScreen : SupportAppScreen() {

        override fun getFragment() = UsersFragment()
    }
}
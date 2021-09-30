package com.posse.android.karaoke.activity

import com.posse.android.karaoke.navigation.AndroidScreens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(AndroidScreens.SongsScreen())
    }

    fun backPressed() {
        router.exit()
    }
}
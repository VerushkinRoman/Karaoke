package com.posse.android.karaoke.activity

import com.posse.android.karaoke.navigation.AndroidScreens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter @Inject constructor(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(AndroidScreens.SongsScreen())
    }

    fun backPressed() {
        router.exit()
    }
}
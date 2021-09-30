package com.posse.android.karaoke.screens.songs

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface SongsView: MvpView {
    fun init()
    fun updateList()
}
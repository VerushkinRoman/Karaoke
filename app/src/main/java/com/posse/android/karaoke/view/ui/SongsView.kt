package com.posse.android.karaoke.view.ui

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface SongsView: MvpView {
    fun init()
    fun updateList()
}
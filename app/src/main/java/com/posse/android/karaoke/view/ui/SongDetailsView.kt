package com.posse.android.karaoke.view.ui

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd

@AddToEnd
interface SongDetailsView : MvpView {
    fun init(caption: String)
    fun updateView(description: String)
}
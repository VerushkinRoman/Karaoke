package com.posse.android.karaoke.view.ui

import android.graphics.Bitmap
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd

@AddToEnd
interface SongDetailsView : MvpView {
    fun init(caption: String)
    fun updateDescription(description: String)
    fun updatePicture(picture: Bitmap)
}
package com.posse.android.karaoke.screens.songDetails

import android.graphics.Bitmap
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd

@AddToEnd
interface SongDetailsView : MvpView {
    fun updateCaption(artist: String, track: String)
    fun updateDescription(description: String)
    fun updatePicture(pictureUrl: String)
}
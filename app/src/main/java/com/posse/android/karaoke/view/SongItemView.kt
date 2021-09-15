package com.posse.android.karaoke.view

interface SongItemView : IItemView {

    fun showCaption(caption: String)
    fun getID(): String
    fun setID(id: String)
}
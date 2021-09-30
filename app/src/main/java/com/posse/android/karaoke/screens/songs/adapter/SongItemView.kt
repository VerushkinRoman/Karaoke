package com.posse.android.karaoke.screens.songs.adapter

import com.posse.android.karaoke.items.IItemView

interface SongItemView : IItemView {

    fun showCaption(caption: String)
}
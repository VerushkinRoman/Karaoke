package com.posse.android.karaoke.items

import com.posse.android.karaoke.screens.songs.adapter.SongItemView

interface IListPresenter<V : IItemView> {

    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface ISongListPresenter : IListPresenter<SongItemView>
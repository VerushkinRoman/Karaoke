package com.posse.android.karaoke.presentation

import com.posse.android.karaoke.view.IItemView
import com.posse.android.karaoke.view.UserItemView

interface IListPresenter<V : IItemView> {

    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>
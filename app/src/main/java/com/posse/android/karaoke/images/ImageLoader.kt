package com.posse.android.karaoke.images

import android.graphics.drawable.Drawable

interface ImageLoader<T> {

    fun loadTo(url: String, view: T, callback: (Drawable?) -> Unit)
}
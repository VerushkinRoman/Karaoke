package com.posse.android.karaoke.utils

import android.content.Context
import android.graphics.drawable.Drawable
import java.io.File

interface FilesystemWorker {
    fun saveFile(directory: File, name: String, drawable: Drawable?)
    fun getDrawable(directory: File, name: String, context: Context): Drawable?
}
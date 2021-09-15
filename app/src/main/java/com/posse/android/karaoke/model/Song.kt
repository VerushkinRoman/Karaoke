package com.posse.android.karaoke.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Song(
    val caption: String,
    val id: String
) : Parcelable

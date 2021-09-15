package com.posse.android.karaoke.model

class SongRepo {
    private val description = "Some loaded song description"

    fun getDescription(song: Song) = "$description ${song.caption}"
}
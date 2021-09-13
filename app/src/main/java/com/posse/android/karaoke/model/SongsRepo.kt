package com.posse.android.karaoke.model

class SongsRepo {

    private val songs = listOf(
        Song("song1"),
        Song("song2"),
        Song("song3"),
        Song("song4"),
        Song("song5")
    )

    fun getSongs() = songs
}
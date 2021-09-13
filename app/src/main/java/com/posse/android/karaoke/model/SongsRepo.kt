package com.posse.android.karaoke.model

class SongsRepo {

    private val songs = listOf(
        Song("user1"),
        Song("user2"),
        Song("user3"),
        Song("user4"),
        Song("user5")
    )

    fun getSongs() = songs
}
package com.posse.android.karaoke.model

import java.util.*

object SongsRepo {

    private lateinit var currentSong: Song

    private val songs = listOf(
        Song("song1", getID()),
        Song("song2", getID()),
        Song("song3", getID()),
        Song("song4", getID()),
        Song("song5", getID())
    )

    fun setCurrentSong(id: String) {
        songs.forEach {
            if (it.id == id) currentSong = it
        }
    }

    fun getCurrentSong() = currentSong

    fun getSongs() = songs

    private fun getID() = UUID.randomUUID().toString()
}
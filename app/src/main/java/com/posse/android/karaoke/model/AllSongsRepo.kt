package com.posse.android.karaoke.model

import io.reactivex.rxjava3.core.Single

interface AllSongsRepo {

    fun getSongsList(songs: List<Song>): Single<List<Song>>
    fun getOfflineSongsList(): Single<List<Song>>

    fun getSingleSong(song: SingleSong): Single<SingleSong>
    fun getOfflineSingleSong(artist: String, track: String): Single<SingleSong>
}
package com.posse.android.karaoke.model

import com.posse.android.karaoke.remote.ApiHolder
import io.reactivex.rxjava3.core.Single

class SongsRepo {

    fun getSongs(): Single<List<TopSongs.Tracks.Song>> = ApiHolder
        .apiService
        .getSongs()
        .map { it.tracks.songsList }
}
package com.posse.android.karaoke.model

import com.posse.android.karaoke.remote.ApiHolder
import io.reactivex.rxjava3.core.Single

class SongRepo {

    fun getSong(artist: String, track: String): Single<Song> = ApiHolder
        .apiService
        .getSong(artist, track)
}
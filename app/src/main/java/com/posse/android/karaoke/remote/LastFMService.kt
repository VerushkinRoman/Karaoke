package com.posse.android.karaoke.remote

import com.posse.android.karaoke.BuildConfig
import com.posse.android.karaoke.model.SingleSong
import com.posse.android.karaoke.model.TopSongs
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFMService {

    @GET(".")
    fun getSongs(
        @Query("format") format: String = "json",
        @Query("method") method: String = "tag.gettoptracks",
        @Query("api_key") api_key: String = BuildConfig.LASTFM_SERVER_KEY,
        @Query("tag") tag: String = "rock"
    ): Single<TopSongs>

    @GET(".")
    fun getSong(
        @Query("artist") artist: String,
        @Query("track") track: String,
        @Query("format") format: String = "json",
        @Query("method") method: String = "track.getInfo",
        @Query("api_key") api_key: String = BuildConfig.LASTFM_SERVER_KEY
    ): Single<SingleSong>
}
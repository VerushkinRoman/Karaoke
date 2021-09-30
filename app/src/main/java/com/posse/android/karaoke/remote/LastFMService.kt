package com.posse.android.karaoke.remote

import com.posse.android.karaoke.BuildConfig
import com.posse.android.karaoke.model.Song
import com.posse.android.karaoke.model.TopSongs
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFMService {

    @GET("?method=tag.gettoptracks&tag=rock&api_key=${BuildConfig.LASTFM_SERVER_KEY}&format=json")
    fun getSongs(): Single<TopSongs>

    @GET("?method=track.getInfo&api_key=${BuildConfig.LASTFM_SERVER_KEY}&format=json")
    fun getSong(@Query("artist") artist: String, @Query("track") track: String): Single<Song>
}
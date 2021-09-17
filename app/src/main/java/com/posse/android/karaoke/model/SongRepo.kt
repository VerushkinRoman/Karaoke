package com.posse.android.karaoke.model

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.posse.android.karaoke.App
import com.posse.android.karaoke.R
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class SongRepo {
    private val description = "Some loaded song description"

    fun getDescription(song: Song) = "$description ${song.caption}"

    fun getSongPicture(): Single<Bitmap> = Single.create<Bitmap> {
        val songPicture = BitmapFactory.decodeResource(App.instance.resources, R.drawable.song_logo)
        if (songPicture != null) {
            it.onSuccess(songPicture)
        } else {
            it.onError(RuntimeException("No song picture"))
        }
    }.delay(2, TimeUnit.SECONDS)
}
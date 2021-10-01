package com.posse.android.karaoke.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SingleSong(
    @Expose val track: Track
) : Parcelable

@Parcelize
data class Track(
    @Expose val album: Album,
    @Expose val artist: Artist,
    @Expose val duration: String,
    @Expose val name: String
) : Parcelable

@Parcelize
data class Album(
    @Expose val image: List<AlbumImage>
) : Parcelable

@Parcelize
data class AlbumImage(
    @SerializedName("#text")
    @Expose val pictureUrl: String,
    @Expose val size: String
) : Parcelable
package com.posse.android.karaoke.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Song(
    @Expose val track: Track
) : Parcelable {

    @Parcelize
    data class Track(
        @Expose val album: Album,
        @Expose val artist: Artist,
        @Expose val duration: String,
        @Expose val name: String
    ) : Parcelable {

        @Parcelize
        data class Album(
            @Expose val artist: String,
            @Expose val image: List<AlbumImage>,
            @Expose val title: String,
        ) : Parcelable {

            @Parcelize
            data class AlbumImage(
                @SerializedName("#text")
                @Expose val pictureUrl: String,
                @Expose val size: String
            ) : Parcelable
        }

        @Parcelize
        data class Artist(
            @Expose val name: String
        ) : Parcelable
    }
}
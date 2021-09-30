package com.posse.android.karaoke.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TopSongs(
    @Expose val tracks: Tracks
) : Parcelable {

    @Parcelize
    data class Tracks(
        @SerializedName("track")
        @Expose val songsList: List<Song>
    ) : Parcelable {

        @Parcelize
        data class Song(
            @Expose val artist: Artist,
            @Expose val duration: String,
            @Expose val name: String
        ) : Parcelable {

            @Parcelize
            data class Artist(
                @Expose val name: String,
            ) : Parcelable
        }
    }
}

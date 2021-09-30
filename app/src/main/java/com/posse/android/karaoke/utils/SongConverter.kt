package com.posse.android.karaoke.utils

import com.posse.android.karaoke.model.*
import com.posse.android.karaoke.model.db.RoomSong

fun convertRoomSongToRetrofitSong(roomSong: RoomSong): SingleSong {
    return SingleSong(
        Track(
            album = Album(
                image = listOf(
                    AlbumImage(
                        pictureUrl = roomSong.imageUrl,
                        size = "extralarge"
                    )
                )
            ),
            artist = Artist(
                name = roomSong.artist
            ),
            duration = roomSong.duration,
            name = roomSong.name
        )
    )
}

fun convertRetrofitSongToRoomSong(
    song: SingleSong,
    songInDB: RoomSong?,
    pictureUrl: String?
): RoomSong {
    return RoomSong(
        id = song.track.artist.name + song.track.name,
        name = song.track.name,
        artist = song.track.artist.name,
        text = songInDB?.text ?: "",
        imageUrl = pictureUrl ?: "",
        duration = song.track.duration
    )
}
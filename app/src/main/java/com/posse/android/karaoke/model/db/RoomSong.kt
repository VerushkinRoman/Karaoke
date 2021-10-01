package com.posse.android.karaoke.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomSong(
    @PrimaryKey val id: String,
    val name: String,
    val artist: String,
    val text: String,
    val imageUrl: String,
    val duration: String
)
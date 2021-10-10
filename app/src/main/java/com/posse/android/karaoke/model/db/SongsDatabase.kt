package com.posse.android.karaoke.model.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RoomSong::class],
    version = 1
)
abstract class SongsDatabase : RoomDatabase() {

    abstract val songDao: SongDao
}
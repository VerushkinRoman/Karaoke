package com.posse.android.karaoke.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [RoomSong::class],
    version = 1
)
abstract class SongsDatabase : RoomDatabase() {

    abstract val songDao: SongDao

    companion object {

        private const val DB_NAME = "database.db"

        private var instance: SongsDatabase? = null

        fun getInstance() = instance ?: throw IllegalStateException("DB not initialized")

        fun create(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, SongsDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
    }
}
package com.posse.android.karaoke.di.modules

import android.content.Context
import androidx.room.Room
import com.posse.android.karaoke.model.AllSongsRepo
import com.posse.android.karaoke.model.AllSongsRepoImpl
import com.posse.android.karaoke.model.db.SongsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    @Singleton
    fun db(context: Context): SongsDatabase {
        return Room.databaseBuilder(context, SongsDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    companion object {

        private const val DB_NAME = "database.db"
    }

    @Provides
    fun allSongsRepo(db: SongsDatabase): AllSongsRepo = AllSongsRepoImpl(db)
}
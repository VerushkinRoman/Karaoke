package com.posse.android.karaoke.model.db

import androidx.room.*

@Dao
interface SongDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(song: RoomSong)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg songs: RoomSong)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(songs: List<RoomSong>)

    @Update
    fun update(song: RoomSong)

    @Update
    fun update(vararg songs: RoomSong)

    @Update
    fun update(songs: List<RoomSong>)

    @Delete
    fun delete(song: RoomSong)

    @Delete
    fun delete(vararg songs: RoomSong)

    @Delete
    fun delete(songs: List<RoomSong>)

    @Query("SELECT * FROM RoomSong")
    fun getAll(): List<RoomSong>

    @Query("SELECT * FROM RoomSong WHERE name = :songName")
    fun getBySongName(songName: String): List<RoomSong>?

    @Query("SELECT * FROM RoomSong WHERE artist = :artist")
    fun getByArtist(artist: String): List<RoomSong>?

    @Query("SELECT * FROM RoomSong WHERE id = :id")
    fun getById(id: String): RoomSong?
}
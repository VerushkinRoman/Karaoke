package com.posse.android.karaoke.model

import com.posse.android.karaoke.model.db.RoomSong
import com.posse.android.karaoke.model.db.SongsDatabase
import com.posse.android.karaoke.utils.convertRetrofitSongToRoomSong
import com.posse.android.karaoke.utils.convertRoomSongToRetrofitSong
import io.reactivex.rxjava3.core.Single

class AllSongsRepoImpl(override val db: SongsDatabase) : AllSongsRepo {

    override fun getSongsList(songs: List<Song>): Single<List<Song>> {
        return Single.fromCallable {
            val roomSongs = songs.map { song ->
                val songInDB =
                    db.songDao.getById(song.artist.name + song.name)
                RoomSong(
                    id = songInDB?.id ?: song.artist.name + song.name,
                    name = song.name,
                    artist = song.artist.name,
                    text = songInDB?.text ?: "",
                    imageUrl = songInDB?.imageUrl ?: "",
                    duration = song.duration
                )
            }
            db.songDao.insert(roomSongs)
            songs
        }
    }

    override fun getOfflineSongsList(): Single<List<Song>> {
        return Single.fromCallable {
            db.songDao.getAll().map { roomSong ->
                Song(
                    artist = Artist(roomSong.artist),
                    duration = roomSong.duration,
                    name = roomSong.name
                )
            }
        }
    }

    override fun getSingleSong(song: SingleSong): Single<SingleSong> {
        return Single.fromCallable {
            val songInDB =
                db.songDao.getById(song.track.artist.name + song.track.name)
            var roomSong: RoomSong? = null
            song.track.album.image.forEach {
                if (it.size == "extralarge") roomSong =
                    convertRetrofitSongToRoomSong(song, songInDB, it.pictureUrl)
            }
            roomSong?.let {
                db.songDao.insert(it)
            }
            song
        }
    }

    override fun getOfflineSingleSong(
        artist: String,
        track: String
    ): Single<SingleSong> {
        return Single.fromCallable {
            val roomSong = db.songDao.getById(artist + track)
            roomSong?.let {
                convertRoomSongToRetrofitSong(it)
            }
        }
    }
}
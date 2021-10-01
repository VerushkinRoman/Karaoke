package com.posse.android.karaoke.model

import com.posse.android.karaoke.remote.ApiHolder
import com.posse.android.karaoke.utils.NetworkStatus
import io.reactivex.rxjava3.core.Single

class SongsRepo(
    private val networkStatus: NetworkStatus,
    private val allSongsRepo: AllSongsRepo
) {

    fun getSongs(): Single<List<Song>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                ApiHolder
                    .apiService
                    .getSongs()
                    .map { it.tracks.songsList }
                    .flatMap { songs ->
                        allSongsRepo.getSongsList(songs)
                    }
            } else {
                allSongsRepo.getOfflineSongsList()
            }
        }
}
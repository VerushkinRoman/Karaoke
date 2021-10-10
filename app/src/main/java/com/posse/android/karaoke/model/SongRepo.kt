package com.posse.android.karaoke.model

import com.posse.android.karaoke.remote.IApiHolder
import com.posse.android.karaoke.utils.NetworkStatus
import io.reactivex.rxjava3.core.Single

class SongRepo(
    private val networkStatus: NetworkStatus,
    private val apiHolder: IApiHolder,
    private val allSongsRepo: AllSongsRepo
) {

    fun getSong(artist: String, track: String): Single<SingleSong> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                apiHolder
                    .apiService
                    .getSong(artist, track)
                    .flatMap { song ->
                        allSongsRepo.getSingleSong(song)
                    }
            } else {
                allSongsRepo.getOfflineSingleSong(artist, track)
            }
        }
}
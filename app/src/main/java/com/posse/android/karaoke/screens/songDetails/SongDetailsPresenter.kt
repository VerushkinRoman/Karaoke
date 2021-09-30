package com.posse.android.karaoke.screens.songDetails

import android.os.Handler
import android.os.Looper
import com.posse.android.karaoke.model.Song
import com.posse.android.karaoke.model.SongRepo
import com.posse.android.karaoke.model.TopSongs
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class SongDetailsPresenter(
    private val router: Router,
    private val song: TopSongs.Tracks.Song
) : MvpPresenter<SongDetailsView>() {

    private val repo = SongRepo()
    private var songDisposable: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData(song)
    }

    private fun loadData(song: TopSongs.Tracks.Song) {
        val handler = Handler(Looper.getMainLooper())

        val fullSong = object : SingleObserver<Song> {
            override fun onSubscribe(d: Disposable) {
                songDisposable = d
            }

            override fun onSuccess(t: Song) {
                handler.post {
                    viewState.updateCaption(t.track.artist.name, t.track.name)
                    t.track.album.image.forEach {
                        if (it.size == "extralarge") {
                            viewState.updatePicture(it.pictureUrl)
                        }
                    }
                }
            }

            override fun onError(e: Throwable) {
                throw RuntimeException("Missing song: $e")
            }

        }

        repo.getSong(song.artist.name, song.name)
            .subscribeOn(Schedulers.io())
            .subscribe(fullSong)
    }

    fun backPressed(): Boolean {
        songDisposable?.dispose()
        router.exit()
        return true
    }
}
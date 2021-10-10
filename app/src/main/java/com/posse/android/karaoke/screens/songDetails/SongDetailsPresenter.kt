package com.posse.android.karaoke.screens.songDetails

import com.posse.android.karaoke.model.SingleSong
import com.posse.android.karaoke.model.Song
import com.posse.android.karaoke.model.SongRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class SongDetailsPresenter(
    private val song: Song
) : MvpPresenter<SongDetailsView>() {

    @Inject
    lateinit var repo: SongRepo

    @Inject
    lateinit var router: Router

    private var songDisposable: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData(song)
    }

    private fun loadData(song: Song) {

        val fullSong = object : SingleObserver<SingleSong> {
            override fun onSubscribe(d: Disposable) {
                songDisposable = d
            }

            override fun onSuccess(t: SingleSong) {
                viewState.updateCaption(t.track.artist.name, t.track.name)
                t.track.album.image.forEach {
                    if (it.size == "extralarge") {
                        viewState.updatePicture(it.pictureUrl)
                    }
                }
            }

            override fun onError(e: Throwable) {
                throw RuntimeException("Missing song: $e")
            }

        }

        repo.getSong(song.artist.name, song.name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(fullSong)
    }

    fun backPressed(): Boolean {
        songDisposable?.dispose()
        router.exit()
        return true
    }
}
package com.posse.android.karaoke.presentation

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import com.posse.android.karaoke.model.Song
import com.posse.android.karaoke.model.SongRepo
import com.posse.android.karaoke.model.SongsRepo
import com.posse.android.karaoke.view.ui.SongDetailsView
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import java.io.ByteArrayOutputStream

class SongDetailsPresenter(
    private val router: Router
) : MvpPresenter<SongDetailsView>() {

    private val repo = SongRepo()
    private var disposable: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        val song = SongsRepo.getCurrentSong()
        viewState.init(song.caption)
        loadData(song)

    }

    private fun loadData(song: Song) {
        viewState.updateDescription(repo.getDescription(song))

        val handler = Handler(Looper.getMainLooper())

        val picture = object : SingleObserver<Bitmap> {
            override fun onSubscribe(d: Disposable) {
                disposable = d
            }

            override fun onSuccess(t: Bitmap) {
                Thread.sleep(2000)
                val byteArrayOutputStream = ByteArrayOutputStream()
                t.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
                val bitmapData = byteArrayOutputStream.toByteArray()
                val bitmap = BitmapFactory.decodeByteArray(bitmapData, 0, bitmapData.size)
                handler.post { viewState.updatePicture(bitmap) }
            }

            override fun onError(e: Throwable) {
                throw RuntimeException("Can't convert picture: $e")
            }

        }

        repo.getSongPicture()
            .retry(5)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe(picture)
    }

    fun backPressed(): Boolean {
        disposable?.dispose()
        router.exit()
        return true
    }
}
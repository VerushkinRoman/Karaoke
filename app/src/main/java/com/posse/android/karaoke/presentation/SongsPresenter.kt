package com.posse.android.karaoke.presentation

import com.posse.android.karaoke.model.Song
import com.posse.android.karaoke.model.SongsRepo
import com.posse.android.karaoke.screens.AndroidScreens
import com.posse.android.karaoke.view.SongItemView
import com.posse.android.karaoke.view.ui.SongsView
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class SongsPresenter(
    private val songsRepo: SongsRepo,
    private val router: Router
) : MvpPresenter<SongsView>() {

    private var disposable: Disposable? = null

    class SongsListPresenter : ISongListPresenter {

        val songs = mutableListOf<Song>()

        override var itemClickListener: ((SongItemView) -> Unit)? = null

        override fun getCount(): Int = songs.size

        override fun bindView(view: SongItemView) {
            val song = songs[view.pos]
            view.showCaption(song.caption)
            view.setID(song.id)
        }
    }

    val songsListPresenter = SongsListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()

        songsListPresenter.itemClickListener = { itemView ->
            val currentId = itemView.getID()
            songsListPresenter.songs.forEach {
                if (it.id == currentId) {
                    songsRepo.setCurrentSong(currentId)
                    router.navigateTo(AndroidScreens.SongDetailsScreen())
                }
            }
        }
    }

    private fun loadData() {
        val songs = object : Observer<Song> {

            override fun onSubscribe(d: Disposable) {
                disposable = d
            }

            override fun onNext(t: Song) {
                songsListPresenter.songs.add(t)
            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {
                viewState.updateList()
            }
        }
        songsRepo.getSongs().subscribe(songs)
    }

    fun backPressed(): Boolean {
        disposable?.dispose()
        router.exit()
        return true
    }
}
package com.posse.android.karaoke.presentation

import com.posse.android.karaoke.model.Song
import com.posse.android.karaoke.model.SongsRepo
import com.posse.android.karaoke.view.SongItemView
import com.posse.android.karaoke.view.ui.SongsView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class SongsPresenter(
    private val songsRepo: SongsRepo,
    private val router: Router
) : MvpPresenter<SongsView>() {

    class SongsListPresenter : ISongListPresenter {

        val songs = mutableListOf<Song>()

        override var itemClickListener: ((SongItemView) -> Unit)? = null

        override fun getCount(): Int = songs.size

        override fun bindView(view: SongItemView) {
            val song = songs[view.pos]
            view.showCaption(song.caption)
        }
    }

    val songsListPresenter = SongsListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()

        songsListPresenter.itemClickListener = { itemView ->
            // todo
        }
    }

    private fun loadData() {
        val songs = songsRepo.getSongs()
        songsListPresenter.songs.addAll(songs)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
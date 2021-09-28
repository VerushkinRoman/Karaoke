package com.posse.android.karaoke.screens.songs

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.posse.android.karaoke.items.ISongListPresenter
import com.posse.android.karaoke.model.SongsRepo
import com.posse.android.karaoke.model.TopSongs
import com.posse.android.karaoke.navigation.AndroidScreens
import com.posse.android.karaoke.screens.songs.adapter.SongItemView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class SongsPresenter(
    private val songsRepo: SongsRepo,
    private val router: Router
) : MvpPresenter<SongsView>() {

    class SongsListPresenter : ISongListPresenter {

        val songs = mutableListOf<TopSongs.Tracks.Song>()

        override var itemClickListener: ((SongItemView) -> Unit)? = null

        override fun getCount(): Int = songs.size

        override fun bindView(view: SongItemView) {
            val song = songs[view.pos]
            view.showCaption("${song.name} - ${song.artist.name}")
            view.id = song.name
        }
    }

    val songsListPresenter = SongsListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()

        songsListPresenter.itemClickListener = { itemView ->
            val currentId = itemView.id
            songsListPresenter.songs.forEach {
                if (it.name == currentId) {
                    router.navigateTo(AndroidScreens.SongDetailsScreen(it))
                }
            }
        }
    }

    private fun loadData() {
        val handler = Handler(Looper.getMainLooper())
        songsRepo.getSongs().subscribe({
            songsListPresenter.songs.clear()
            songsListPresenter.songs.addAll(it)
            handler.post {
                viewState.updateList()
            }
        }, {
            it.message?.let { it1 -> Log.d("error", it1) }
        })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
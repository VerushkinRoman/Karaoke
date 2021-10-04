package com.posse.android.karaoke.screens.songs

import android.util.Log
import com.posse.android.karaoke.items.ISongListPresenter
import com.posse.android.karaoke.model.Song
import com.posse.android.karaoke.model.SongsRepo
import com.posse.android.karaoke.navigation.AndroidScreens
import com.posse.android.karaoke.screens.songs.adapter.SongItemView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class SongsPresenter : MvpPresenter<SongsView>() {

    @Inject
    lateinit var songsRepo: SongsRepo

    @Inject
    lateinit var router: Router

    class SongsListPresenter : ISongListPresenter {

        val songs = mutableListOf<Song>()

        override var itemClickListener: ((SongItemView) -> Unit)? = null

        override fun getCount(): Int = songs.size

        override fun bindView(view: SongItemView) {
            val song = songs[view.pos]
            view.showCaption("${song.name} - ${song.artist.name}")
            view.id = song.artist.name + song.name
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
                if (it.artist.name + it.name == currentId) {
                    router.navigateTo(AndroidScreens.SongDetailsScreen(it))
                }
            }
        }
    }

    private fun loadData() {
        songsRepo.getSongs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                songsListPresenter.songs.clear()
                songsListPresenter.songs.addAll(it)
                viewState.updateList()
            }, {
                it.message?.let { error -> Log.d("error", error) }
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
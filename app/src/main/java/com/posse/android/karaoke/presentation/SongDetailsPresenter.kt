package com.posse.android.karaoke.presentation

import com.posse.android.karaoke.model.Song
import com.posse.android.karaoke.model.SongRepo
import com.posse.android.karaoke.model.SongsRepo
import com.posse.android.karaoke.view.ui.SongDetailsView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class SongDetailsPresenter(
    private val router: Router
) : MvpPresenter<SongDetailsView>() {

    private val repo = SongRepo()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        val song = SongsRepo.getCurrentSong()
        viewState.init(song.caption)
        loadData(song)

    }

    private fun loadData(song: Song) {
        viewState.updateView(repo.getDescription(song))
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
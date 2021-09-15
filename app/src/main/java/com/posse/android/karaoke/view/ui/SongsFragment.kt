package com.posse.android.karaoke.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.posse.android.karaoke.App
import com.posse.android.karaoke.databinding.FragmentSongsBinding
import com.posse.android.karaoke.model.SongsRepo
import com.posse.android.karaoke.presentation.SongsPresenter
import com.posse.android.karaoke.view.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class SongsFragment : MvpAppCompatFragment(), SongsView, BackButtonListener {

    private var vb: FragmentSongsBinding? = null

    private val presenter by moxyPresenter {
        SongsPresenter(
            SongsRepo,
            App.instance.router
        )
    }

    private val adapter by lazy { SongsRVAdapter(presenter.songsListPresenter) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSongsBinding.inflate(inflater, container, false).also {
            vb = it
        }.root
    }

    override fun init() {
        vb?.rvSongs?.layoutManager = LinearLayoutManager(requireContext())
        vb?.rvSongs?.adapter = adapter
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }
}
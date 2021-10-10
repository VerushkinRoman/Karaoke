package com.posse.android.karaoke.screens.songs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.posse.android.karaoke.App
import com.posse.android.karaoke.databinding.FragmentSongsBinding
import com.posse.android.karaoke.navigation.BackButtonListener
import com.posse.android.karaoke.screens.songs.adapter.SongsRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class SongsFragment : MvpAppCompatFragment(), SongsView, BackButtonListener {

    private var _binding: FragmentSongsBinding? = null
    private val binding get() = _binding!!

    private val presenter by moxyPresenter {
        App.instance.initSongsSubcomponent()
        SongsPresenter().apply {
            App.instance.songsSubcomponent?.inject(this)
        }
    }

    private val adapter by lazy { SongsRVAdapter(presenter.songsListPresenter) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSongsBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root
    }

    override fun init() {
        binding.rvSongs.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSongs.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }
}
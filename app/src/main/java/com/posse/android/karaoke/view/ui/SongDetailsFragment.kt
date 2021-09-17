package com.posse.android.karaoke.view.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.posse.android.karaoke.App
import com.posse.android.karaoke.databinding.FragmentSongDetailsBinding
import com.posse.android.karaoke.presentation.SongDetailsPresenter
import com.posse.android.karaoke.view.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class SongDetailsFragment : MvpAppCompatFragment(), SongDetailsView,
    BackButtonListener {

    private var _binding: FragmentSongDetailsBinding? = null
    private val binding get() = _binding!!

    private val presenter by moxyPresenter {
        SongDetailsPresenter(
            App.instance.router
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSongDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loadingButton.setOnClickListener { presenter.backPressed() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

    override fun init(caption: String) {
        binding.songCaption.text = caption
    }

    override fun updateDescription(description: String) {
        binding.songDescription.text = description
        binding.songDescription.visibility = View.VISIBLE
    }

    override fun updatePicture(picture: Bitmap) {
        binding.songPicture.setImageBitmap(picture)
        binding.songPicture.visibility = View.VISIBLE
        binding.loadingLayout.visibility = View.GONE
        binding.songLayout.visibility = View.VISIBLE
    }
}
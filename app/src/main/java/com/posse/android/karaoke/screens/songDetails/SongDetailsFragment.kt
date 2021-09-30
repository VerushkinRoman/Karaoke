package com.posse.android.karaoke.screens.songDetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.posse.android.karaoke.App
import com.posse.android.karaoke.databinding.FragmentSongDetailsBinding
import com.posse.android.karaoke.images.GlideImageLoader
import com.posse.android.karaoke.model.TopSongs
import com.posse.android.karaoke.navigation.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class SongDetailsFragment : MvpAppCompatFragment(), SongDetailsView,
    BackButtonListener {

    private var _binding: FragmentSongDetailsBinding? = null
    private val binding get() = _binding!!
    private val imageLoader = GlideImageLoader()

    private val presenter by moxyPresenter {
        SongDetailsPresenter(
            App.instance.router,
            arguments?.getParcelable("SONG")!!
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

    @SuppressLint("SetTextI18n")
    override fun updateCaption(artist: String, track: String) {
        binding.songCaption.text = "$artist - $track"
    }

    override fun updateDescription(description: String) {
        binding.songDescription.text = description
        binding.songDescription.visibility = View.VISIBLE
    }

    override fun updatePicture(pictureUrl: String) {
        val handler = Handler(Looper.getMainLooper())
        imageLoader.loadTo(pictureUrl, binding.songPicture) {
            it?.let {
                handler.post {
                    if (this@SongDetailsFragment.isVisible) {
                        binding.songPicture.setImageDrawable(it)
                        binding.songPicture.visibility = View.VISIBLE
                        binding.loadingLayout.visibility = View.GONE
                    }
                }
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(song: TopSongs.Tracks.Song) = SongDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("SONG", song)
            }
        }
    }
}
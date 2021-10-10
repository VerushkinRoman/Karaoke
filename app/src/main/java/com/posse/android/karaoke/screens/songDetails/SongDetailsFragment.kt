package com.posse.android.karaoke.screens.songDetails

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.posse.android.karaoke.App
import com.posse.android.karaoke.databinding.FragmentSongDetailsBinding
import com.posse.android.karaoke.images.ImageLoader
import com.posse.android.karaoke.model.Song
import com.posse.android.karaoke.navigation.BackButtonListener
import com.posse.android.karaoke.utils.FilesystemWorker
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

private const val KEY_SONG = "SONG"

class SongDetailsFragment : MvpAppCompatFragment(), SongDetailsView,
    BackButtonListener {

    private var _binding: FragmentSongDetailsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var filesystem: FilesystemWorker

    @Inject
    lateinit var imageLoader: ImageLoader<ImageView>

    private val presenter by moxyPresenter {
        SongDetailsPresenter(arguments?.getParcelable(KEY_SONG)!!).apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
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
        imageLoader.loadTo(pictureUrl, binding.songPicture) { picture ->
            val drawable: Drawable? = if (picture != null) {
                Thread {
                    filesystem.saveFile(App.instance.cacheDir, pictureUrl, picture)
                }.start()
                picture
            } else {
                filesystem.getDrawable(App.instance.cacheDir, pictureUrl, requireContext())
            }
            view?.post {
                binding.songPicture.setImageDrawable(drawable)
                binding.songPicture.visibility = View.VISIBLE
                binding.loadingLayout.visibility = View.GONE
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(song: Song) = SongDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY_SONG, song)
            }
        }
    }
}
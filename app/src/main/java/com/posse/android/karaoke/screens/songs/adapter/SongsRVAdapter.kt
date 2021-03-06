package com.posse.android.karaoke.screens.songs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.posse.android.karaoke.databinding.ItemSongBinding
import com.posse.android.karaoke.items.ISongListPresenter

class SongsRVAdapter(
    private val presenter: ISongListPresenter
) : RecyclerView.Adapter<SongsRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSongBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }
    }

    override fun getItemCount(): Int = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    inner class ViewHolder(private val vb: ItemSongBinding) : RecyclerView.ViewHolder(vb.root),
        SongItemView {

        override var pos: Int = -1
        override var id: String = ""

        override fun showCaption(caption: String) {
            vb.songCaption.text = caption
        }
    }
}
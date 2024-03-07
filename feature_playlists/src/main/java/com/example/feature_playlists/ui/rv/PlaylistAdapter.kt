package com.example.feature_playlists.ui.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.feature_playlists.R
import com.example.feature_playlists.databinding.CardPlaylistBinding
import com.example.remote.models.ui.PlaylistUi

class PlaylistAdapter(
    private val onItemClickListener: OnItemClickListener,
) : PagingDataAdapter<PlaylistUi, PlaylistAdapter.ViewHolder>(PlaylistUiDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_playlist, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { playlistUi ->
            holder.init(playlistUi)
        }
    }

    inner class ViewHolder(
        private val view: View,
    ) : RecyclerView.ViewHolder(view) {

        fun init(playlistUi: PlaylistUi) {
            CardPlaylistBinding.bind(view).apply {
                name.text = playlistUi.name

                Glide.with(view)
                    .load(playlistUi.imageUrl)
                    .centerCrop()
                    .into(image)

                root.setOnClickListener {
                    onItemClickListener.onClick(playlistUi)
                }

            }
        }

    }

    fun interface OnItemClickListener {
        fun onClick(playlistUi: PlaylistUi)
    }

    private object PlaylistUiDiffItemCallback : DiffUtil.ItemCallback<PlaylistUi>() {

        override fun areItemsTheSame(oldItem: PlaylistUi, newItem: PlaylistUi): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PlaylistUi, newItem: PlaylistUi): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
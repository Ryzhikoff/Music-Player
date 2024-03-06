package com.example.feature_playlists.ui.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.feature_playlists.R
import com.example.feature_playlists.databinding.CardPlaylistBinding
import com.example.feature_playlists.models.PlaylistUi

class PlaylistAdapter(
    private val onItemClickListener: OnItemClickListener,
) : RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {

    private var items: MutableList<PlaylistUi> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_playlist, parent, false)

        )
    }


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.init(items[position])
    }

    fun submitList(newList: List<PlaylistUi>) {
        val diff = PlaylistsDiff(items, newList)
        val diffResult = DiffUtil.calculateDiff(diff)
        items.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
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

    private class PlaylistsDiff(
        private val oldList: List<PlaylistUi>,
        private val newList: List<PlaylistUi>,
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

    }
}
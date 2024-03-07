package com.example.features_tracks.ui.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.features_tracks.R
import com.example.features_tracks.databinding.CardTrackBinding
import com.example.remote.models.ui.TracksUi

class TracksAdapter(

) : PagingDataAdapter<TracksUi, TracksAdapter.ViewHolder>(TrackUiDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_track, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { track ->
            holder.init(track)
        }
    }

    inner class ViewHolder(
        private val view: View,
    ) : RecyclerView.ViewHolder(view) {
        fun init(track: TracksUi) {
            CardTrackBinding.bind(view).apply {
                name.text = track.name
                author.text = track.author

                track.imageUrl?.let { url ->
                    Glide.with(view)
                        .load(url)
                        .fitCenter()
                        .into(image)
                }
            }

        }
    }

    private object TrackUiDiffItemCallback : DiffUtil.ItemCallback<TracksUi>() {

        override fun areItemsTheSame(oldItem: TracksUi, newItem: TracksUi): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TracksUi, newItem: TracksUi): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
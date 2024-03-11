package com.example.features_tracks.ui.rv

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_player.domain.MediaPlayerState
import com.example.features_tracks.ui.customview.CardTrack
import com.example.core.ui.models.TrackUi
import com.example.feature_player.domain.Paused
import com.example.feature_player.domain.Playing
import com.example.feature_player.domain.Stopped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TracksAdapter(
    private val onActionClickListener: OnActionClickListener,
    private val onStopClickListener: OnStopClickListener,
    private val coroutineScope: CoroutineScope,
) : PagingDataAdapter<TrackUi, TracksAdapter.ViewHolder>(TrackUiDiffItemCallback) {

    private var playingPosition = -1

    init {
        setPlayerListener()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardTrack = CardTrack(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        return ViewHolder(cardTrack)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { track ->

            coroutineScope.launch {
                MediaPlayerState.stateListener.collectLatest { state ->
                    if (state is Playing) {
                        if (state.trackUi.id == track.id && !track.isPlaying) {
                            track.isPlaying = true
                        }
                    }
                    holder.init(track, position)
                }
            }
        }
    }

    private fun setPlayerListener() {
        coroutineScope.launch {
            MediaPlayerState.stateListener.collectLatest { state ->
                if (state is Stopped || state is Paused) {
                    if (playingPosition != -1) {
                        getItem(playingPosition)?.let { track ->
                            if (track.isPlaying) {
                                track.isPlaying = false
                                notifyItemChanged(playingPosition, Unit)
                            }
                        }
                    }
                }

            }
        }
    }


    inner class ViewHolder(
        private val cardTrack: CardTrack,
    ) : RecyclerView.ViewHolder(cardTrack) {
        fun init(track: TrackUi, position: Int) {
            cardTrack.apply {

                setContent(track)
                setOnPlayPauseClickListener {
                    track.isPlaying = !track.isPlaying

                    if (playingPosition != -1 && position != playingPosition) {
                        getItem(playingPosition)?.let {
                            it.isPlaying = false
                            notifyItemChanged(playingPosition, Unit)
                        }
                    }
                    playingPosition = position

                    onActionClickListener.onClick(track)
                }
                setOnStopClickListener {
                    if (track.isPlaying) {
                        track.isPlaying = false
                        onStopClickListener.onClick(track)
                    }
                }
            }
        }
    }

    interface OnActionClickListener {
        fun onClick(trackUrl: TrackUi)
    }

    interface OnStopClickListener {
        fun onClick(trackUrl: TrackUi)
    }

    private object TrackUiDiffItemCallback : DiffUtil.ItemCallback<TrackUi>() {

        override fun areItemsTheSame(oldItem: TrackUi, newItem: TrackUi): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TrackUi, newItem: TrackUi): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
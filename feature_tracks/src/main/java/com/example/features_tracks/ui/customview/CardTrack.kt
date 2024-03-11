package com.example.features_tracks.ui.customview

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.example.features_tracks.R
import com.example.features_tracks.databinding.CardTrackBinding
import com.example.core.ui.models.TrackUi

class CardTrack @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding: CardTrackBinding

    private var trackUi: TrackUi? = null

    init {
        binding = CardTrackBinding.bind(inflate(context, R.layout.card_track, this))
    }

    fun setContent(trackUi: TrackUi) = with(binding) {
        this@CardTrack.trackUi = trackUi

        name.text = trackUi.name
        author.text = trackUi.author

        trackUi.imageUrl?.let { url ->
            Glide.with(this@CardTrack)
                .load(url)
                .fitCenter()
                .into(image)
        }
        actionButton.isSelected = trackUi.isPlaying
    }

    fun setOnPlayPauseClickListener(callback: (TrackUi) -> Unit) {
        binding.actionButton.setOnClickListener { imageView ->
            imageView.isSelected = !imageView.isSelected
            trackUi?.let {
                callback(it)
            }
        }
    }

    fun setOnStopClickListener(callback: (TrackUi) -> Unit) {
        binding.stop.setOnClickListener { _ ->
            binding.actionButton.isSelected = false
            trackUi?.let {
                callback(it)
            }
        }
    }

}
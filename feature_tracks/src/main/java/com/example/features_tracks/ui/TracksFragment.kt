package com.example.features_tracks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.core.ui.rv.SpacingItemDecoration
import com.example.features_tracks.R
import com.example.features_tracks.databinding.FragmentTracksBinding
import com.example.features_tracks.di.TrackComponentProvider
import com.example.features_tracks.ui.rv.TracksAdapter
import com.example.features_tracks.utils.TracksViewModeFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class TracksFragment : Fragment(R.layout.fragment_tracks) {
    private var _binding: FragmentTracksBinding? = null
    private val binding get() = _binding!!

    private val adapter = TracksAdapter()

    @Inject
    lateinit var viewModelFactory: TracksViewModeFactory

    private val viewModel: TracksViewModel by viewModels { viewModelFactory}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTracksBinding.bind(view)

        (requireActivity().application as TrackComponentProvider)
            .getTrackComponent()
            .inject(this)

        arguments?.getInt(KEY_PLAYLIST_ID).also { playlistId ->
            playlistId?.let {
                viewModel.getTracks(playlistId)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.tracksFlow.collectLatest {
                adapter.submitData(lifecycle, it)
            }
        }

        initRV()

    }

    private fun initRV() {
        binding.recyclerView.apply {
            adapter = this@TracksFragment.adapter
            addItemDecoration(SpacingItemDecoration(paddingBottomInDp = MARGIN))
        }

        adapter.addLoadStateListener { state ->
            with(binding) {
                recyclerView.isVisible = state.refresh != LoadState.Loading
                progressBar.isVisible = state.refresh == LoadState.Loading
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY_PLAYLIST_ID = "key_playlist_id"
        private const val MARGIN = 8
    }
}
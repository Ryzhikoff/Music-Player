package com.example.feature_playlists.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.feature_playlists.R
import com.example.feature_playlists.databinding.FragmentPlaylistsBinding
import com.example.feature_playlists.di.PlaylistsComponentProvider
import com.example.feature_playlists.ui.rv.PlaylistAdapter
import com.example.core.ui.rv.SpacingItemDecoration
import com.example.feature_playlists.utils.PlaylistsViewModeFactory
import com.example.features_tracks.ui.TracksFragment.Companion.KEY_PLAYLIST_ID
import com.example.remote.models.ui.PlaylistUi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlaylistsFragment : Fragment(R.layout.fragment_playlists) {
    private var _binding: FragmentPlaylistsBinding? = null
    private val binding get() = _binding!!

    private val onItemClickListener = PlaylistAdapter.OnItemClickListener {
        navigateToTracks(it)
    }

    private val adapter = PlaylistAdapter(onItemClickListener)

    @Inject
    lateinit var viewModelProvider: PlaylistsViewModeFactory
    private val viewModel: PlaylistsViewModel by viewModels { viewModelProvider }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPlaylistsBinding.bind(view)

        (requireActivity().application as PlaylistsComponentProvider)
            .getPlaylistsComponent()
            .inject(this)

        initRecyclerView()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.playlists.collectLatest {
                adapter.submitData(lifecycle, it)
            }
        }

    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            adapter = this@PlaylistsFragment.adapter

            addItemDecoration(
                SpacingItemDecoration(
                    paddingTopInDp = MARGIN_IN_DP,
                    paddingStartInDp = MARGIN_IN_DP,
                    paddingEndInDp = MARGIN_IN_DP,
                    paddingBottomInDp = MARGIN_IN_DP
                )
            )
        }

        adapter.addLoadStateListener { state ->
            with(binding) {
                recyclerView.isVisible = state.refresh != LoadState.Loading
                progressBar.isVisible = state.refresh == LoadState.Loading
            }
        }
    }

    private fun navigateToTracks(playlistUi: PlaylistUi) {
        findNavController().navigate(
            R.id.action_playlistsFragment_to_tracksFragment,
            bundleOf(Pair(KEY_PLAYLIST_ID, playlistUi.id))
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private companion object {
        const val MARGIN_IN_DP = 8
    }
}
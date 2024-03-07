package com.example.feature_playlists.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.feature_playlists.R
import com.example.feature_playlists.databinding.FragmentPlaylistsBinding
import com.example.feature_playlists.di.PlaylistsComponentProvider
import com.example.feature_playlists.ui.rv.PlaylistAdapter
import com.example.feature_playlists.ui.rv.SpacingItemDecoration
import com.example.feature_playlists.utils.PlaylistsViewModeFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlaylistsFragment : Fragment(R.layout.fragment_playlists) {
    private var _binding: FragmentPlaylistsBinding? = null
    private val binding get() = _binding!!

    private val onItemClickListener = PlaylistAdapter.OnItemClickListener {
        println(it)
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private companion object {
        const val MARGIN_IN_DP = 8
    }
}
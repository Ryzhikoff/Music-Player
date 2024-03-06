package com.example.feature_playlists.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feature_playlists.R
import com.example.feature_playlists.databinding.FragmentPlaylistsBinding
import com.example.feature_playlists.models.PlaylistUi
import com.example.feature_playlists.ui.rv.PlaylistAdapter
import com.example.feature_playlists.ui.rv.SpacingItemDecoration
import kotlin.random.Random

class PlaylistsFragment : Fragment(R.layout.fragment_playlists) {
    private var _binding: FragmentPlaylistsBinding? = null
    private val binding get() = _binding!!

    private val onItemClickListener = PlaylistAdapter.OnItemClickListener {
        println(it)
    }

    private val adapter = PlaylistAdapter(onItemClickListener)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPlaylistsBinding.bind(view)

        println("PlaylistsFragment onViewCreated")
        initRecyclerView()
        addFakeData()
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

    private fun addFakeData() {
        val list = mutableListOf<PlaylistUi>()
        repeat(100) {
            list.add(
                PlaylistUi(
                    id = it,
                    name = "PLaylist Names",
                    imageUrl = "https://my.enter.yoga/upload/covers/playlists/image_image_352_55034_cover_839_83564.png"
                )
            )
        }
        adapter.submitList(list)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private companion object {
        const val MARGIN_IN_DP = 8
    }
}
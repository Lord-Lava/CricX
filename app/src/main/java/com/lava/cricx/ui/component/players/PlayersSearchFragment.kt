package com.lava.cricx.ui.component.players

import android.view.View
import androidx.fragment.app.viewModels
import com.lava.cricx.R
import com.lava.cricx.data.Resource
import com.lava.cricx.data.dto.players.TrendingPlayersDto
import com.lava.cricx.databinding.FragmentPlayersSearchBinding
import com.lava.cricx.ui.base.BaseFragment
import com.lava.cricx.util.extensions.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayersSearchFragment :
    BaseFragment<FragmentPlayersSearchBinding>(R.layout.fragment_players_search) {

    private val viewModel: PlayerSearchViewModel by viewModels()

    override fun initViewBinding(view: View): FragmentPlayersSearchBinding =
        FragmentPlayersSearchBinding.bind(view)

    override fun setupViews() {
        //TODO
    }

    private fun handleTrendingPlayersList(status: Resource<TrendingPlayersDto>) {
        when (status) {
            is Resource.Loading -> {

            }
            is Resource.Success -> {

            }
            is Resource.DataError -> {

                status.errorCode?.let { viewModel.showToastMessage(it) }
            }
        }
    }

    override fun observeViewModel() {
        observe(viewModel.trendingPlayersList, ::handleTrendingPlayersList)
    }
}
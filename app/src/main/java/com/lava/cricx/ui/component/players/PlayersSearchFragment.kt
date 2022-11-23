package com.lava.cricx.ui.component.players

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.lava.cricx.R
import com.lava.cricx.data.Resource
import com.lava.cricx.data.dto.players.PlayersListDto
import com.lava.cricx.data.dto.mapper.toPlayersList
import com.lava.cricx.data.dto.players.Player
import com.lava.cricx.databinding.FragmentPlayersSearchBinding
import com.lava.cricx.domain.model.players.PlayersList
import com.lava.cricx.ui.base.BaseFragment
import com.lava.cricx.ui.component.players.adapter.SearchedPlayersAdapter
import com.lava.cricx.ui.component.players.adapter.TrendingPlayersAdapter
import com.lava.cricx.util.SingleEvent
import com.lava.cricx.util.extensions.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayersSearchFragment :
    BaseFragment<FragmentPlayersSearchBinding>(R.layout.fragment_players_search) {

    private val viewModel: PlayerSearchViewModel by viewModels()
    private lateinit var trendingPlayersAdapter: TrendingPlayersAdapter
    private lateinit var searchedPlayersAdapter: SearchedPlayersAdapter

    override fun initViewBinding(view: View): FragmentPlayersSearchBinding =
        FragmentPlayersSearchBinding.bind(view)

    override fun setupViews() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvPlayersList.layoutManager = layoutManager

        binding.etPlayerName.afterTextChanged {
            if (it.isEmpty()) {
                viewModel.getTrendingPlayers()
            } else if (it.isNotEmpty() && it.length >= 2) {
                viewModel.searchPlayer(it)
            }
        }
    }

    private fun handleTrendingPlayersList(status: Resource<PlayersListDto>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> status.data?.toPlayersList()
                .let { bindTrendingPlayersListData(playersList = it) }
            is Resource.DataError -> {
                showDataView(false)
                status.errorCode?.let { viewModel.showToastMessage(it) }
            }
        }
    }

    private fun handleSearchedPlayersList(status: Resource<PlayersListDto>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> status.data?.toPlayersList().let {
                bindSearchedPlayersListData(playersList = it)
            }
            is Resource.DataError -> {
                showDataView(false)
                status.errorCode?.let { viewModel.showToastMessage(it) }
            }
        }
    }

    private fun showLoadingView() {
        binding.pbLoading.visible()
        binding.rvPlayersList.gone()
    }

    private fun showDataView(show: Boolean) {
        binding.pbLoading.visibility = if (show) GONE else VISIBLE
        binding.rvPlayersList.visibility = if (show) VISIBLE else GONE
    }

    private fun bindTrendingPlayersListData(playersList: PlayersList?) {
        if (!playersList?.playersList.isNullOrEmpty()) {
            trendingPlayersAdapter = TrendingPlayersAdapter(viewModel, playersList!!.playersList)
            binding.rvPlayersList.adapter = trendingPlayersAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

    private fun bindSearchedPlayersListData(playersList: PlayersList?) {
        if (!playersList?.playersList.isNullOrEmpty()) {
            searchedPlayersAdapter = SearchedPlayersAdapter(viewModel, playersList!!.playersList)
            binding.rvPlayersList.adapter = searchedPlayersAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Toast.LENGTH_LONG)
    }

    override fun observeViewModel() {
        observe(liveData = viewModel.trendingPlayersList, action = ::handleTrendingPlayersList)
        observe(liveData = viewModel.searchedPlayersResponseList,
            action = ::handleSearchedPlayersList)
        observeToast(viewModel.showToast)
    }
}
package com.lava.cricx.ui.component.players

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lava.cricx.R
import com.lava.cricx.data.Resource
import com.lava.cricx.databinding.FragmentPlayersSearchBinding
import com.lava.cricx.domain.model.players.PlayersList
import com.lava.cricx.ui.base.BaseFragment
import com.lava.cricx.ui.component.players.adapter.searchedPlayers.SearchedPlayersAdapter
import com.lava.cricx.ui.component.players.adapter.trendingPlayers.TrendingPlayersAdapter
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
        val layoutManager = LinearLayoutManager(context)
        binding.rvPlayersList.layoutManager = layoutManager
        setupAppBar()
    }

    private fun setupAppBar() {
        activity?.findViewById<AppCompatTextView>(R.id.tvToolbarTitle)?.text = getString(R.string.browse_players)
        activity?.findViewById<AppCompatImageView>(R.id.ivNavDrawer)?.gone()
        activity?.findViewById<AppCompatImageView>(R.id.ivBackArrow)?.visible()
    }

    override fun onResume() {
        super.onResume()
        binding.etPlayerName.afterTextChanged {
            viewModel.updateSearchQuery(it)
        }
    }

    private fun handleSearchQuery(event: SingleEvent<String>) {
        event.getContentIfNotHandled()?.let { query ->
            if (query.isEmpty()) {
                binding.tvTrendingPlayersHeader.visible()
                viewModel.cancelPlayerSearch()
                if (viewModel.trendingPlayersList.value != null)
                    bindTrendingPlayersListData(viewModel.trendingPlayersList.value?.data)
                else
                    viewModel.getTrendingPlayers()
            } else if (query.isNotEmpty() && query.length >= 2) {
                binding.tvTrendingPlayersHeader.gone()
                viewModel.searchPlayer(query)
            }
        }
    }

    private fun handleTrendingPlayersList(status: Resource<PlayersList>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> bindTrendingPlayersListData(playersList = status.data)
            is Resource.DataError -> {
                showDataView(false)
                status.errorCode?.let { viewModel.showToastMessage(it) }
            }
        }
    }

    private fun handleSearchedPlayersList(status: Resource<PlayersList>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> bindSearchedPlayersListData(playersList = status.data)
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

    private fun handleNavigation(event: SingleEvent<String>) {
        event.getContentIfNotHandled()?.let { playerId ->
            this.findNavController().navigate(PlayersSearchFragmentDirections.actionPlayersSearchFragmentToPlayerInfoActivity(playerId))
        }
    }

    override fun observeViewModel() {
        observe(liveData = viewModel.trendingPlayersList, action = ::handleTrendingPlayersList)
        observe(liveData = viewModel.searchedPlayersResponseList, action = ::handleSearchedPlayersList)
        observeEvent(liveData = viewModel.searchQuery, action = ::handleSearchQuery)
        observeEvent(liveData = viewModel.navigateToPlayerInfoFragment, action = ::handleNavigation)
        observeToast(viewModel.showToast)
    }
}
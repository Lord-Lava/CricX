package com.lava.cricx.ui.component.players

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lava.cricx.data.Resource
import com.lava.cricx.data.dto.players.PlayersListDto
import com.lava.cricx.domain.repository.PlayersRepository
import com.lava.cricx.ui.base.BaseViewModel
import com.lava.cricx.util.SingleEvent
import com.lava.cricx.util.wrapEspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerSearchViewModel @Inject constructor(
    private val playersRepository: PlayersRepository,
) : BaseViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val _trendingPlayersList = MutableLiveData<Resource<PlayersListDto>>()
    val trendingPlayersList = _trendingPlayersList as LiveData<Resource<PlayersListDto>>

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val _searchedPlayersResponseList = MutableLiveData<Resource<PlayersListDto>>()
    val searchedPlayersResponseList =
        _searchedPlayersResponseList as LiveData<Resource<PlayersListDto>>

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val _showToast = MutableLiveData<SingleEvent<Any>>()
    val showToast = _showToast as LiveData<SingleEvent<Any>>

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private var searchPlayerJob: Job? = null

    init {
        getTrendingPlayers()
    }

    fun getTrendingPlayers() {
        viewModelScope.launch {
            _trendingPlayersList.value = Resource.Loading()
            wrapEspressoIdlingResource {
                playersRepository.getTrendingPlayers().collect {
                    _trendingPlayersList.value = it
                }
            }
        }
    }

    fun searchPlayer(playerName: String) {
        _searchedPlayersResponseList.value = Resource.Loading()
        searchPlayerJob = viewModelScope.launch {
            wrapEspressoIdlingResource {
                delay(300L)
                playersRepository.searchPlayer(playerName).collectLatest {
                    _searchedPlayersResponseList.value = it
                }
            }
        }
    }

    fun cancelPlayerSearch() {
        searchPlayerJob?.cancel()
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        _showToast.value = SingleEvent(error.description)
    }
}
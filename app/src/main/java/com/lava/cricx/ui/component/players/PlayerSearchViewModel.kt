package com.lava.cricx.ui.component.players

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lava.cricx.data.Resource
import com.lava.cricx.data.dto.players.TrendingPlayersDto
import com.lava.cricx.domain.repository.PlayersRepository
import com.lava.cricx.ui.base.BaseViewModel
import com.lava.cricx.util.SingleEvent
import com.lava.cricx.util.wrapEspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerSearchViewModel @Inject constructor(
    private val playersRepository: PlayersRepository
) : BaseViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val _trendingPlayersList = MutableLiveData<Resource<TrendingPlayersDto>>()
    val trendingPlayersList = _trendingPlayersList as LiveData<Resource<TrendingPlayersDto>>

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val _showToast = MutableLiveData<SingleEvent<Any>>()
    val showToast = _showToast as LiveData<SingleEvent<Any>>

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

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        _showToast.value = SingleEvent(error.description)
    }
}
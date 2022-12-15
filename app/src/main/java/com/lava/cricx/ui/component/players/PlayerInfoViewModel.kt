package com.lava.cricx.ui.component.players

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.lava.cricx.data.Resource
import com.lava.cricx.domain.model.players.PlayerCareer
import com.lava.cricx.domain.model.players.PlayerInfo
import com.lava.cricx.domain.model.players.Stats
import com.lava.cricx.domain.repository.PlayersRepository
import com.lava.cricx.ui.base.BaseViewModel
import com.lava.cricx.util.SingleEvent
import com.lava.cricx.util.wrapEspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerInfoViewModel @Inject constructor(
    private val playersRepository: PlayersRepository,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    val playerId = savedStateHandle.get<String>("playerId")

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val _playerInfo = MutableLiveData<Resource<PlayerInfo>>()
    val playerInfo = _playerInfo as LiveData<Resource<PlayerInfo>>

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val _playerCareer = MutableLiveData<Resource<PlayerCareer>>()
    val playerCareer = _playerCareer as LiveData<Resource<PlayerCareer>>

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val _battingStats = MutableLiveData<Resource<Stats>>()
    val battingStats = _battingStats as LiveData<Resource<Stats>>

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val _bowlingStats = MutableLiveData<Resource<Stats>>()
    val bowlingStats = _bowlingStats as LiveData<Resource<Stats>>

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val _showToast = MutableLiveData<SingleEvent<Any>>()
    val showToast = _showToast as LiveData<SingleEvent<Any>>

    fun getPlayerInfo(playerId: String) {
        viewModelScope.launch {
            _playerInfo.value = Resource.Loading()
            wrapEspressoIdlingResource {
                playersRepository.getPlayerInfo(playerId).collectLatest {
                    _playerInfo.value = it
                }
            }
        }
    }

    fun getPlayerCareer(playerId: String) {
        viewModelScope.launch {
            _playerCareer.value = Resource.Loading()
            wrapEspressoIdlingResource {
                playersRepository.getPlayerCareer(playerId).collectLatest {
                    _playerCareer.value = it
                }
            }
        }
    }

    fun getBattingStats(playerId: String) {
        viewModelScope.launch {
            _battingStats.value = Resource.Loading()
            wrapEspressoIdlingResource {
                playersRepository.getBattingStats(playerId).collectLatest {
                    _battingStats.value = it
                }
            }
        }
    }

    fun getBowlingStats(playerId: String) {
        viewModelScope.launch {
            _bowlingStats.value = Resource.Loading()
            wrapEspressoIdlingResource {
                playersRepository.getBowlingStats(playerId).collectLatest {
                    _bowlingStats.value = it
                }
            }
        }
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        _showToast.value = SingleEvent(error.description)
    }
}
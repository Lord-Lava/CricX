package com.lava.cricx.data.remote.source

import com.lava.cricx.data.Resource
import com.lava.cricx.data.dto.mapper.toBattingStats
import com.lava.cricx.data.dto.mapper.toPlayerCareer
import com.lava.cricx.data.dto.mapper.toPlayerInfo
import com.lava.cricx.data.dto.mapper.toPlayersList
import com.lava.cricx.data.dto.players.PlayersListDto
import com.lava.cricx.data.dto.players.battingStats.StatsDto
import com.lava.cricx.data.dto.players.career.PlayerCareerDto
import com.lava.cricx.data.dto.players.info.PlayerInfoDto
import com.lava.cricx.data.error.NETWORK_ERROR
import com.lava.cricx.data.error.NO_CONTENT
import com.lava.cricx.data.error.NO_INTERNET_CONNECTION
import com.lava.cricx.data.remote.service.PlayersService
import com.lava.cricx.domain.model.players.PlayerCareer
import com.lava.cricx.domain.model.players.PlayerInfo
import com.lava.cricx.domain.model.players.PlayersList
import com.lava.cricx.domain.model.players.Stats
import com.lava.cricx.util.NetworkConnectivity
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class PlayersData @Inject constructor(
    private val playersService: PlayersService,
    private val networkConnectivity: NetworkConnectivity,
) : PlayersDataSource {

    override suspend fun getTrendingPlayers(): Resource<PlayersList> {
        return when (val response = processCall(playersService::getTrendingPlayers)) {
            is PlayersListDto -> {
                Resource.Success(data = response.toPlayersList())
            }
            else -> {
                Resource.DataError(errorCode = if (response == null) NO_CONTENT else response as Int)
            }
        }
    }

    override suspend fun searchPlayer(playerName: String): Resource<PlayersList> {
        return when (val response =
            processCall { playersService.searchPlayer(playerName = playerName) }) {
            is PlayersListDto -> {
                Resource.Success(data = response.toPlayersList())
            }
            else -> {
                Resource.DataError(errorCode = if (response == null) NO_CONTENT else response as Int)
            }
        }
    }

    override suspend fun getPlayerInfo(playerId: String): Resource<PlayerInfo> {
        return when (val response =
            processCall { playersService.getPlayerInfo(playerId = playerId) }) {
            is PlayerInfoDto -> {
                Resource.Success(data = response.toPlayerInfo())
            }
            else -> {
                Resource.DataError(errorCode = if (response == null) NO_CONTENT else response as Int)
            }
        }
    }

    override suspend fun getBattingStats(playerId: String): Resource<Stats> {
        return when (val response =
            processCall { playersService.getBattingStats(playerId = playerId) }) {
            is StatsDto -> {
                Resource.Success(data = response.toBattingStats())
            }
            else -> {
                Resource.DataError(errorCode = if (response == null) NO_CONTENT else response as Int)
            }
        }
    }

    override suspend fun getBowlingStats(playerId: String): Resource<Stats> {
        return when (val response =
            processCall { playersService.getBowlingStats(playerId = playerId) }) {
            is StatsDto -> {
                Resource.Success(data = response.toBattingStats())
            }
            else -> {
                Resource.DataError(errorCode = if (response == null) NO_CONTENT else response as Int)
            }
        }
    }

    override suspend fun getPlayerCareer(playerId: String): Resource<PlayerCareer> {
        return when (val response =
            processCall { playersService.getPlayerCareer(playerId = playerId) }) {
            is PlayerCareerDto -> {
                Resource.Success(data = response.toPlayerCareer())
            }
            else -> {
                Resource.DataError(errorCode = if (response == null) NO_CONTENT else response as Int)
            }
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}
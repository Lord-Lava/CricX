package com.lava.cricx.data.remote.source

import com.lava.cricx.data.Resource
import com.lava.cricx.data.dto.players.PlayersListDto
import com.lava.cricx.data.error.NETWORK_ERROR
import com.lava.cricx.data.error.NO_CONTENT
import com.lava.cricx.data.error.NO_INTERNET_CONNECTION
import com.lava.cricx.data.remote.service.PlayersService
import com.lava.cricx.util.NetworkConnectivity
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class PlayersData @Inject constructor(
    private val playersService: PlayersService,
    private val networkConnectivity: NetworkConnectivity,
) : PlayersDataSource {

    override suspend fun getTrendingPlayers(): Resource<PlayersListDto> {
        return when (val response = processCall(playersService::getTrendingPlayers)) {
            is PlayersListDto -> {
                Resource.Success(data = response)
            }
            else -> {
                Resource.DataError(errorCode = if (response == null) NO_CONTENT else response as Int)
            }
        }
    }

    override suspend fun searchPlayer(playerName: String): Resource<PlayersListDto> {
        return when (val response =
            processCall { playersService.searchPlayer(playerName = playerName) }) {
            is PlayersListDto -> {
                Resource.Success(data = response)
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
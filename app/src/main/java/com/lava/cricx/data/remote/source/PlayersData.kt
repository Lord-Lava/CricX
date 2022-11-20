package com.lava.cricx.data.remote.source

import com.lava.cricx.data.Resource
import com.lava.cricx.data.dto.players.TrendingPlayersDto
import com.lava.cricx.data.error.NETWORK_ERROR
import com.lava.cricx.data.error.NO_INTERNET_CONNECTION
import com.lava.cricx.data.remote.service.PlayersService
import com.lava.cricx.util.NetworkConnectivity
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class PlayersData @Inject constructor(private val playersService: PlayersService, private val networkConnectivity: NetworkConnectivity): PlayersDataSource {

    override suspend fun getTrendingPlayers(): Resource<TrendingPlayersDto> {
        return when (val response = processCall(playersService::getTrendingPlayers)) {
            is TrendingPlayersDto -> {
                Resource.Success(data = response)
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
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
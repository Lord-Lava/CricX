package com.lava.cricx.data.remote.service

import com.lava.cricx.BuildConfig
import com.lava.cricx.data.dto.players.TrendingPlayersDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface PlayersService {

    // Returns list of trending player searches
    @GET("/players/list-trending")
    suspend fun getTrendingPlayers(
        @Header("X-RapidApi-Key") apiKey: String = BuildConfig.API_KEY
    ) : Response<TrendingPlayersDto>


}
package com.lava.cricx.data.remote.service

import com.lava.cricx.BuildConfig
import com.lava.cricx.data.dto.players.PlayersListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PlayersService {

    @GET("/players/list-trending")
    suspend fun getTrendingPlayers(
        @Header("X-RapidApi-Key") apiKey: String = BuildConfig.API_KEY,
    ): Response<PlayersListDto>

    @GET("/players/search")
    suspend fun searchPlayer(
        @Header("X-RapidApi-Key") apiKey: String = BuildConfig.API_KEY,
        @Query("plrN") playerName: String,
    ): Response<PlayersListDto>


}
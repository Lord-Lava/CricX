package com.lava.cricx.data.remote.service

import com.lava.cricx.BuildConfig
import com.lava.cricx.data.dto.players.PlayersListDto
import com.lava.cricx.data.dto.players.battingStats.StatsDto
import com.lava.cricx.data.dto.players.career.PlayerCareerDto
import com.lava.cricx.data.dto.players.info.PlayerInfoDto
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

    @GET("/players/get-info")
    suspend fun getPlayerInfo(
        @Header("X-RapidApi-Key") apiKey: String = BuildConfig.API_KEY,
        @Query("playerId") playerId: String,
    ): Response<PlayerInfoDto>

    @GET("/players/get-career")
    suspend fun getPlayerCareer(
        @Header("X-RapidApi-Key") apiKey: String = BuildConfig.API_KEY,
        @Query("playerId") playerId: String,
    ): Response<PlayerCareerDto>

    @GET("/players/get-batting")
    suspend fun getBattingStats(
        @Header("X-RapidApi-Key") apiKey: String = BuildConfig.API_KEY,
        @Query("playerId") playerId: String,
    ): Response<StatsDto>

    @GET("/players/get-bowling")
    suspend fun getBowlingStats(
        @Header("X-RapidApi-Key") apiKey: String = BuildConfig.API_KEY,
        @Query("playerId") playerId: String,
    ): Response<StatsDto>

}
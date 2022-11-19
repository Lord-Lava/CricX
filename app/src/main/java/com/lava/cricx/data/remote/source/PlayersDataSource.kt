package com.lava.cricx.data.remote.source

import com.lava.cricx.BuildConfig
import com.lava.cricx.data.Resource
import com.lava.cricx.data.dto.players.TrendingPlayersDto

interface PlayersDataSource {

    suspend fun getTrendingPlayers(): Resource<TrendingPlayersDto>
}
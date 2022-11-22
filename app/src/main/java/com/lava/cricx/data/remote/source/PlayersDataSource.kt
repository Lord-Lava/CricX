package com.lava.cricx.data.remote.source

import com.lava.cricx.data.Resource
import com.lava.cricx.data.dto.players.PlayersListDto

interface PlayersDataSource {

    suspend fun getTrendingPlayers(): Resource<PlayersListDto>

    suspend fun searchPlayer(playerName: String): Resource<PlayersListDto>
}
package com.lava.cricx.domain.repository

import com.lava.cricx.data.Resource
import com.lava.cricx.data.dto.players.PlayersListDto
import kotlinx.coroutines.flow.Flow

interface PlayersRepository {

    suspend fun getTrendingPlayers(): Flow<Resource<PlayersListDto>>

    suspend fun searchPlayer(playerName: String): Flow<Resource<PlayersListDto>>
}
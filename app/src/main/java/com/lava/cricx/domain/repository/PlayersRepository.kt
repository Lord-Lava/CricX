package com.lava.cricx.domain.repository

import com.lava.cricx.data.Resource
import com.lava.cricx.domain.model.players.PlayerCareer
import com.lava.cricx.domain.model.players.PlayerInfo
import com.lava.cricx.domain.model.players.PlayersList
import com.lava.cricx.domain.model.players.Stats
import kotlinx.coroutines.flow.Flow

interface PlayersRepository {

    suspend fun getTrendingPlayers(): Flow<Resource<PlayersList>>

    suspend fun searchPlayer(playerName: String): Flow<Resource<PlayersList>>

    suspend fun getPlayerInfo(playerId: String): Flow<Resource<PlayerInfo>>

    suspend fun getPlayerCareer(playerId: String): Flow<Resource<PlayerCareer>>

    suspend fun getBattingStats(playerId: String): Flow<Resource<Stats>>

    suspend fun getBowlingStats(playerId: String): Flow<Resource<Stats>>
}
package com.lava.cricx.data.remote.source

import com.lava.cricx.data.Resource
import com.lava.cricx.domain.model.players.PlayerCareer
import com.lava.cricx.domain.model.players.PlayerInfo
import com.lava.cricx.domain.model.players.PlayersList
import com.lava.cricx.domain.model.players.Stats

interface PlayersDataSource {

    suspend fun getTrendingPlayers(): Resource<PlayersList>

    suspend fun searchPlayer(playerName: String): Resource<PlayersList>

    suspend fun getPlayerInfo(playerId: String): Resource<PlayerInfo>

    suspend fun getPlayerCareer(playerId: String): Resource<PlayerCareer>

    suspend fun getBattingStats(playerId: String): Resource<Stats>

    suspend fun getBowlingStats(playerId: String): Resource<Stats>
}
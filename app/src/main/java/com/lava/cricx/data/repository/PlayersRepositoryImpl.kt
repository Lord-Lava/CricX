package com.lava.cricx.data.repository

import com.lava.cricx.data.Resource
import com.lava.cricx.data.remote.source.PlayersDataSource
import com.lava.cricx.domain.model.players.PlayerCareer
import com.lava.cricx.domain.model.players.PlayerInfo
import com.lava.cricx.domain.model.players.PlayersList
import com.lava.cricx.domain.model.players.Stats
import com.lava.cricx.domain.repository.PlayersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class PlayersRepositoryImpl @Inject constructor(
    private val playersDataSource: PlayersDataSource,
    private val ioDispatcher: CoroutineContext,
) : PlayersRepository {

    override suspend fun getTrendingPlayers(): Flow<Resource<PlayersList>> {
        return flow {
            emit(playersDataSource.getTrendingPlayers())
        }.flowOn(ioDispatcher)
    }

    override suspend fun searchPlayer(playerName: String): Flow<Resource<PlayersList>> {
        return flow {
            emit(playersDataSource.searchPlayer(playerName))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getPlayerInfo(playerId: String): Flow<Resource<PlayerInfo>> {
        return flow {
            emit(playersDataSource.getPlayerInfo(playerId))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getBattingStats(playerId: String): Flow<Resource<Stats>> {
        return flow {
            emit(playersDataSource.getBattingStats(playerId))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getBowlingStats(playerId: String): Flow<Resource<Stats>> {
        return flow {
            emit(playersDataSource.getBowlingStats(playerId))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getPlayerCareer(playerId: String): Flow<Resource<PlayerCareer>> {
        return flow {
            emit(playersDataSource.getPlayerCareer(playerId))
        }.flowOn(ioDispatcher)
    }
}
package com.lava.cricx.data.repository

import com.lava.cricx.data.Resource
import com.lava.cricx.data.dto.players.TrendingPlayersDto
import com.lava.cricx.data.remote.source.PlayersDataSource
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

    override suspend fun getTrendingPlayers(): Flow<Resource<TrendingPlayersDto>> {
        return flow {
            emit(playersDataSource.getTrendingPlayers())
        }.flowOn(ioDispatcher)
    }
}
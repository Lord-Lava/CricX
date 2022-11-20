package com.lava.cricx.data.mapper

import com.lava.cricx.data.dto.players.TrendingPlayersDto
import com.lava.cricx.domain.model.players.TrendingPlayers

fun TrendingPlayersDto.toTrendingPlayers(): TrendingPlayers {
    return TrendingPlayers(
        category = category,
        playersList = playersList
    )
}
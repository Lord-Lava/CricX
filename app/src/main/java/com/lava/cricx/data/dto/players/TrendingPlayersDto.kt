package com.lava.cricx.data.dto.players

import com.lava.cricx.domain.model.players.Player

data class TrendingPlayersDto(
    val category: String,
    val player: List<Player>
)
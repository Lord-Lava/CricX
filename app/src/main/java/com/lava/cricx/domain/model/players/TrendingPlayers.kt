package com.lava.cricx.domain.model.players

data class TrendingPlayers(
    val category: String,
    val playersList: List<Player>,
)
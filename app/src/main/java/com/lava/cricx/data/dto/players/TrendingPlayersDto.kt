package com.lava.cricx.data.dto.players

import com.lava.cricx.domain.model.players.Player
import com.squareup.moshi.Json

data class TrendingPlayersDto(
    @Json(name = "category")
    val category: String,
    @Json(name = "player")
    val playersList: List<Player>
)
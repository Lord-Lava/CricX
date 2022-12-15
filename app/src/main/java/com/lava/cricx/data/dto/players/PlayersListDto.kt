package com.lava.cricx.data.dto.players

import com.squareup.moshi.Json

data class PlayersListDto(
    @Json(name = "category")
    val category: String = "",
    @Json(name = "player")
    val playersList: List<Player> = emptyList(),
)
package com.lava.cricx.domain.model.players

import com.lava.cricx.data.dto.players.Player

data class PlayersList(
    val category: String,
    val playersList: List<Player>,
)
package com.lava.cricx.domain.model.players

import com.lava.cricx.data.dto.players.battingStats.Row

data class Stats(
    val headers: List<String>,
    val table: List<Row>
)
package com.lava.cricx.data.dto.players.battingStats

import com.squareup.moshi.Json

data class Row(
    @Json(name = "values")
    val columns: List<String> = emptyList()
)
package com.lava.cricx.data.dto.players.battingStats

import com.squareup.moshi.Json

data class StatsDto(
    @Json(name = "appIndex")
    val appIndex: AppIndex,
    @Json(name = "headers")
    val headers: List<String> = emptyList(),
    @Json(name = "seriesSpinner")
    val seriesSpinner: List<SeriesSpinner> = emptyList(),
    @Json(name = "values")
    val table: List<Row> = emptyList()
)
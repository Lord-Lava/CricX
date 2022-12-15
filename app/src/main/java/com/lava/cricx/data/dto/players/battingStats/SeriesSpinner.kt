package com.lava.cricx.data.dto.players.battingStats

import com.squareup.moshi.Json

data class SeriesSpinner(
    @Json(name = "seriesId")
    val seriesId: Int = 0,
    @Json(name = "seriesName")
    val seriesName: String = ""
)
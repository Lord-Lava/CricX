package com.lava.cricx.data.dto.players.battingStats

import com.squareup.moshi.Json

data class AppIndex(
    @Json(name = "seoTitle")
    val seoTitle: String = "",
    @Json(name = "webURL")
    val webURL: String = ""
)
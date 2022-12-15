package com.lava.cricx.data.dto.players.info

import com.squareup.moshi.Json

data class CurrRank(
    @Json(name = "all")
    val all: All,
    @Json(name = "bat")
    val batting: Batting,
    @Json(name = "bowl")
    val bowling: Bowling
)
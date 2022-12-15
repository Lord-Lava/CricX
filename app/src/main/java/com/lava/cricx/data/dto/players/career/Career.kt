package com.lava.cricx.data.dto.players.career

import com.squareup.moshi.Json

data class Career(
    @Json(name = "debut")
    val debut: String = "",
    @Json(name = "lastPlayed")
    val lastPlayed: String = "",
    @Json(name = "name")
    val format: String = ""
)
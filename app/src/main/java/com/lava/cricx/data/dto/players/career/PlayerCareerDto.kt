package com.lava.cricx.data.dto.players.career

import com.squareup.moshi.Json

data class PlayerCareerDto(
    @Json(name = "values")
    val careerList: List<Career> = emptyList(),
)
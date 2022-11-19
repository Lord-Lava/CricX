package com.lava.cricx.domain.model.players

import com.squareup.moshi.Json

data class Player(
    @Json(name = "faceImageId")
    val faceImageId: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "teamName")
    val teamName: String
)
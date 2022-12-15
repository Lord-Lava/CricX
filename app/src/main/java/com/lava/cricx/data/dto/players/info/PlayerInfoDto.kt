package com.lava.cricx.data.dto.players.info

import com.squareup.moshi.Json

data class PlayerInfoDto(
    @Json(name = "DoB")
    val dob: String = "--",
    @Json(name = "DoBFormat")
    val dobFormat: String = "--",
    @Json(name = "appIndex")
    val appIndex: AppIndex,
    @Json(name = "bat")
    val bat: String = "--",
    @Json(name = "bio")
    val bio: String = "--",
    @Json(name = "birthPlace")
    val birthPlace: String = "--",
    @Json(name = "bowl")
    val bowl: String = "--",
    @Json(name = "currRank")
    val currRank: CurrRank,
    @Json(name = "faceImageId")
    val faceImageId: String = "--",
    @Json(name = "height")
    val height: String = "--",
    @Json(name = "id")
    val id: String = "--",
    @Json(name = "image")
    val image: String = "--",
    @Json(name = "intlTeam")
    val initialTeam: List<String> = emptyList(),
    @Json(name = "name")
    val name: String = "--",
    @Json(name = "nickName")
    val nickName: String = "--",
    @Json(name = "role")
    val role: String = "--",
    @Json(name = "teams")
    val teams: List<String> = emptyList(),
)
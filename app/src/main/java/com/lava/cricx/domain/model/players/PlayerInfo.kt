package com.lava.cricx.domain.model.players

import com.lava.cricx.data.dto.players.info.Batting
import com.lava.cricx.data.dto.players.info.Bowling

data class PlayerInfo(
    val dob: String,
    val bat: String,
    val bio: CharSequence,
    val birthPlace: String,
    val bowl: String,
    val battingStats: Batting,
    val bowlingStats: Bowling,
    val faceImageId: String,
    val height: String,
    val id: String,
    val initialTeam: List<String>,
    val name: String,
    val nickName: String,
    val role: String,
    val teams: List<String>,
)
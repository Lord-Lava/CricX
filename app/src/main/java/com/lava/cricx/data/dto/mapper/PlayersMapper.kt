package com.lava.cricx.data.dto.mapper

import android.os.Build
import android.text.Html
import com.lava.cricx.data.dto.players.PlayersListDto
import com.lava.cricx.data.dto.players.battingStats.StatsDto
import com.lava.cricx.data.dto.players.career.PlayerCareerDto
import com.lava.cricx.data.dto.players.info.PlayerInfoDto
import com.lava.cricx.domain.model.players.Stats
import com.lava.cricx.domain.model.players.PlayerCareer
import com.lava.cricx.domain.model.players.PlayerInfo
import com.lava.cricx.domain.model.players.PlayersList

fun PlayersListDto.toPlayersList(): PlayersList {
    return PlayersList(
        category = category,
        playersList = playersList
    )
}

fun PlayerInfoDto.toPlayerInfo(): PlayerInfo {
    val bio = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(bio, Html.FROM_HTML_MODE_COMPACT)
    } else {
        bio
    }
    return PlayerInfo(
        name = name,
        nickName = nickName,
        dob = dob,
        bat = bat,
        bowl = bowl,
        bio = bio,
        birthPlace = birthPlace,
        battingStats = currRank.batting,
        bowlingStats = currRank.bowling,
        faceImageId = faceImageId,
        id = id,
        height = height,
        initialTeam = initialTeam,
        role = role,
        teams = teams[0].split(",").toList()
    )
}

fun PlayerCareerDto.toPlayerCareer(): PlayerCareer {
    return PlayerCareer(
        careerList = careerList
    )
}

fun StatsDto.toBattingStats(): Stats {
    return Stats(
        headers = headers,
        table = table
    )
}
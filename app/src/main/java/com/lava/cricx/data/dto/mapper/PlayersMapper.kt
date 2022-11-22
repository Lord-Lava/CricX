package com.lava.cricx.data.dto.mapper

import com.lava.cricx.data.dto.players.PlayersListDto
import com.lava.cricx.domain.model.players.PlayersList

fun PlayersListDto.toPlayersList(): PlayersList {
    return PlayersList(
        category = category,
        playersList = playersList
    )
}
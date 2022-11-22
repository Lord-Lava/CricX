package com.lava.cricx.ui.base.listeners

import com.lava.cricx.data.dto.players.Player

interface PlayerItemListener {
    fun onPlayerSelected(player: Player)
}
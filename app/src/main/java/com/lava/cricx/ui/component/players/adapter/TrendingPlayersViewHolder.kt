package com.lava.cricx.ui.component.players.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lava.cricx.databinding.ItemTrendingPlayerBinding
import com.lava.cricx.data.dto.players.Player
import com.lava.cricx.ui.base.listeners.PlayerItemListener
import com.lava.cricx.util.glideUrlBuilder

class TrendingPlayersViewHolder(private val itemBinding: ItemTrendingPlayerBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(player: Player, playerItemListener: PlayerItemListener) {
        itemBinding.tvPlayerName.text = player.name
        itemBinding.tvPlayerTeam.text = player.teamName
        Glide.with(itemBinding.ivPlayer).load(glideUrlBuilder(player.faceImageId)).into(itemBinding.ivPlayer)
        itemBinding.clTrendingPlayerItem.setOnClickListener {
            playerItemListener.onPlayerSelected(player)
        }
    }
}
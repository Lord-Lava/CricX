package com.lava.cricx.ui.component.players.adapter.trendingPlayers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lava.cricx.data.dto.players.Player
import com.lava.cricx.databinding.ItemTrendingPlayerBinding
import com.lava.cricx.ui.base.listeners.PlayerItemListener
import com.lava.cricx.ui.component.players.PlayerSearchViewModel
import com.lava.cricx.ui.component.players.adapter.trendingPlayers.TrendingPlayersViewHolder

class TrendingPlayersAdapter(
    private val playerSearchViewModel: PlayerSearchViewModel,
    private val players: List<Player>,
) : RecyclerView.Adapter<TrendingPlayersViewHolder>() {

    private val onItemClickListener: PlayerItemListener = object : PlayerItemListener {
        override fun onPlayerSelected(player: Player) {
            playerSearchViewModel.onPlayerClicked(player.id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingPlayersViewHolder {
        val itemBinding =
            ItemTrendingPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendingPlayersViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: TrendingPlayersViewHolder, position: Int) {
        holder.bind(players[position], onItemClickListener)
    }

    override fun getItemCount(): Int = players.size
}
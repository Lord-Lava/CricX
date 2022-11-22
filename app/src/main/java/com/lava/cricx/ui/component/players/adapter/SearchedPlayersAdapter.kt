package com.lava.cricx.ui.component.players.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lava.cricx.data.dto.players.Player
import com.lava.cricx.databinding.ItemSearchedPlayerBinding
import com.lava.cricx.ui.base.listeners.PlayerItemListener
import com.lava.cricx.ui.component.players.PlayerSearchViewModel

class SearchedPlayersAdapter(
    private val playerSearchViewModel: PlayerSearchViewModel,
    private val players: List<Player>,
) :
    RecyclerView.Adapter<SearchedPlayersViewHolder>() {

    private val onItemClickListener: PlayerItemListener = object : PlayerItemListener {
        override fun onPlayerSelected(player: Player) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedPlayersViewHolder {
        val itemBinding =
            ItemSearchedPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchedPlayersViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SearchedPlayersViewHolder, position: Int) {
        holder.bind(players[position], onItemClickListener)
    }

    override fun getItemCount(): Int = players.size
}
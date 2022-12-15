package com.lava.cricx.ui.component.players.adapter.stats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lava.cricx.data.dto.players.battingStats.Row
import com.lava.cricx.databinding.ItemStatsBinding

class StatsAdapter(
    private val statsTable: List<Row>,
) : RecyclerView.Adapter<StatsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        val itemBinding =
            ItemStatsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StatsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        holder.bind(statsTable[position])
    }

    override fun getItemCount(): Int = statsTable.size
}
package com.lava.cricx.ui.component.players.adapter.stats

import androidx.recyclerview.widget.RecyclerView
import com.lava.cricx.data.dto.players.battingStats.Row
import com.lava.cricx.databinding.ItemStatsBinding
import com.lava.cricx.domain.model.players.Stats

class StatsViewHolder(private val itemBinding: ItemStatsBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(row: Row) {
        itemBinding.tvTitle.text = row.columns[0]
        itemBinding.tvValue1.text = row.columns[1]
        itemBinding.tvValue2.text = row.columns[2]
        itemBinding.tvValue3.text = row.columns[3]
        itemBinding.tvValue4.text = row.columns[4]
    }
}
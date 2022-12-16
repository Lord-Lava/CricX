package com.lava.cricx.ui.component.players.adapter.career

import androidx.recyclerview.widget.RecyclerView
import com.lava.cricx.data.dto.players.career.Career
import com.lava.cricx.databinding.ItemCareerBinding

class CareerViewHolder(private val itemBinding: ItemCareerBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(itemView: Career) {
        itemBinding.tvFormat.text = itemView.format
        itemBinding.tvDebutInfo.text = itemView.debut
        itemBinding.tvLastPlayedInfo.text = itemView.lastPlayed
    }
}
package com.lava.cricx.ui.component.players.adapter.career

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lava.cricx.data.dto.players.career.Career
import com.lava.cricx.databinding.ItemCareerBinding

class CareerAdapter(
    private val careerList: List<Career>,
) : RecyclerView.Adapter<CareerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CareerViewHolder {
        val itemBinding =
            ItemCareerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CareerViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CareerViewHolder, position: Int) {
        holder.bind(careerList[position])
    }

    override fun getItemCount(): Int = careerList.size
}
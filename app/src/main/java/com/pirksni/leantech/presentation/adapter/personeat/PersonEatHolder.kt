package com.pirksni.leantech.presentation.adapter.personeat

import androidx.recyclerview.widget.RecyclerView
import com.pirksni.leantech.databinding.ItemPersonEatBinding

class PersonEatHolder(
    private val binding: ItemPersonEatBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(eat: String) {
        binding.tvNameEat.text = eat
    }
}

package com.pirksni.leantech.presentation.adapter.personeat

import androidx.recyclerview.widget.RecyclerView
import com.pirksni.leantech.databinding.ItemPersonEatBinding
import com.pirksni.leantech.domain.model.PersonEatModel

class PersonEatHolder(
    private val binding: ItemPersonEatBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(personEatModel: PersonEatModel) {
        binding.tvNameEat.text = personEatModel.eat
    }
}

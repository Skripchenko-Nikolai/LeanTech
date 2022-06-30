package com.pirksni.leantech.presentation.adapter.personeat

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.pirksni.leantech.R
import com.pirksni.leantech.databinding.ItemPersonEatBinding
import com.pirksni.leantech.extensions.inflate
import com.pirksni.leantech.extensions.itemCallback

class PersonEatAdapter : ListAdapter<String, PersonEatHolder>(chekDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonEatHolder =
        PersonEatHolder(ItemPersonEatBinding.bind(parent.inflate(R.layout.item_person_eat)))

    override fun onBindViewHolder(holder: PersonEatHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private fun chekDiffUtil(): DiffUtil.ItemCallback<String> =
    itemCallback(
        areItemsTheSame = { new, old ->
            new == old
        }
    )

package com.pirksni.leantech.presentation.adapter.personeat

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.pirksni.leantech.R
import com.pirksni.leantech.databinding.ItemPersonEatBinding
import com.pirksni.leantech.domain.model.PersonEatModel
import com.pirksni.leantech.extensions.inflate
import com.pirksni.leantech.extensions.itemCallback

class PersonEatAdapter : ListAdapter<PersonEatModel, PersonEatHolder>(chekDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonEatHolder =
        PersonEatHolder(ItemPersonEatBinding.bind(parent.inflate(R.layout.item_person_eat)))

    override fun onBindViewHolder(holder: PersonEatHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private fun chekDiffUtil(): DiffUtil.ItemCallback<PersonEatModel> =
    itemCallback(
        areItemsTheSame = { new, old ->
            new.eat == old.eat
        }
    )

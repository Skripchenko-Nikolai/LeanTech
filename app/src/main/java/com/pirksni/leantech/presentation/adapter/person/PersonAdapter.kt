package com.pirksni.leantech.presentation.adapter.person

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.pirksni.leantech.R
import com.pirksni.leantech.databinding.ItemPersonBinding
import com.pirksni.leantech.domain.model.PersonModel
import com.pirksni.leantech.extensions.inflate
import com.pirksni.leantech.extensions.itemCallback

class PersonAdapter(
    private val onItemClick: (PersonModel) -> Unit,
) : ListAdapter<PersonModel, PersonHolder>(chekDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder =
        PersonHolder(ItemPersonBinding.bind(parent.inflate(R.layout.item_person)))

    override fun onBindViewHolder(holder: PersonHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }
}

private fun chekDiffUtil(): DiffUtil.ItemCallback<PersonModel> =
    itemCallback(
        areItemsTheSame = { new, old ->
            new.fullName == old.fullName
        }
    )

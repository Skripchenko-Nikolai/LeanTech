package com.pirksni.leantech.presentation.adapter.person

import androidx.recyclerview.widget.RecyclerView
import com.pirksni.leantech.databinding.ItemPersonBinding
import com.pirksni.leantech.domain.model.PersonModel

class PersonHolder(
    private val binding: ItemPersonBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        personModel: PersonModel,
        onItemClick: (PersonModel) -> Unit
    ) {
        with(binding) {
            root.setOnClickListener {
                onItemClick.invoke(personModel)
            }
            tvNumber.text = personModel.number
            tvFullName.text = personModel.fullName
        }
    }
}

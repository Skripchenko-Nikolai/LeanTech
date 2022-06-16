package com.pirksni.leantech.presentation.screen.filledprofile

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pirksni.leantech.R
import com.pirksni.leantech.databinding.FragmentFilledProfileBinding
import com.pirksni.leantech.extensions.updateVerticalPaddingEdgeToEdge
import com.pirksni.leantech.presentation.base.BaseFragment

class FilledProfileFragment :
    BaseFragment<FilledProfileViewModel>(R.layout.fragment_filled_profile) {

    private val binding by viewBinding(FragmentFilledProfileBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            constraintLayout.updateVerticalPaddingEdgeToEdge()
        }
    }
}

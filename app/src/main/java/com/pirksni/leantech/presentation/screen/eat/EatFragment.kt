package com.pirksni.leantech.presentation.screen.eat

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pirksni.leantech.R
import com.pirksni.leantech.databinding.FragmentEatBinding
import com.pirksni.leantech.presentation.base.BaseFragment

class EatFragment : BaseFragment<EatViewModel>(R.layout.fragment_eat) {

    private val binding by viewBinding(FragmentEatBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.test()
    }
}

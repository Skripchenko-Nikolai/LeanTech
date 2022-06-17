package com.pirksni.leantech.presentation.screen.registration

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pirksni.leantech.R
import com.pirksni.leantech.databinding.FragmentRegistrartionBinding
import com.pirksni.leantech.extensions.updateBottomPaddingEdgeToEdge
import com.pirksni.leantech.extensions.updateTopPaddingEdgeToEdge
import com.pirksni.leantech.presentation.base.BaseFragment

class RegistrationFragment : BaseFragment<RegistrationViewModel>(R.layout.fragment_registrartion) {

    private val binding by viewBinding(FragmentRegistrartionBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            clRegistration.updateTopPaddingEdgeToEdge()
            btnRegister.updateBottomPaddingEdgeToEdge()
        }
    }
}

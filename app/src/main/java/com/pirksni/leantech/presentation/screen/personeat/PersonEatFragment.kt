package com.pirksni.leantech.presentation.screen.personeat

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pirksni.leantech.R
import com.pirksni.leantech.databinding.FragmentPersonEatBinding
import com.pirksni.leantech.extensions.launchWhenStarted
import com.pirksni.leantech.extensions.showSnackbars
import com.pirksni.leantech.extensions.updateTopPaddingEdgeToEdge
import com.pirksni.leantech.presentation.adapter.personeat.PersonEatAdapter
import com.pirksni.leantech.presentation.base.BaseFragment
import kotlinx.coroutines.flow.onEach

class PersonEatFragment : BaseFragment<PersonEatViewModel>(R.layout.fragment_person_eat) {

    private val binding by viewBinding(FragmentPersonEatBinding::bind)

    private val personEatAdapter by lazy {
        PersonEatAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPersonEat.updateTopPaddingEdgeToEdge()
        binding.rvPersonEat.adapter = personEatAdapter
        with(viewModel) {
            onEvent(PersonEatState.Event.OnGetPersonEatSpreadSheet)
            uiLabelFlow.onEach(::handleUiLabel).launchWhenStarted(lifecycleScope)
            stateFlow.onEach(personEatAdapter::submitList).launchWhenStarted(lifecycleScope)
        }
    }


    private fun handleUiLabel(uiLabel: PersonEatState.UiLabel) {
        when (uiLabel) {
            PersonEatState.UiLabel.HideProgressBar ->
                hideProgressBar()
            PersonEatState.UiLabel.ShowProgressBar ->
                showProgressBar()
            PersonEatState.UiLabel.OnExitScreen -> {
                findNavController().navigateUp()
            }
            PersonEatState.UiLabel.None -> {
                // ignore
            }
            is PersonEatState.UiLabel.ErrorInternet -> {
                hideProgressBar()
                if (uiLabel.error == null) {
                    showSnackbars(uiLabel.defaultError)
                } else {
                    showSnackbars(uiLabel.error)
                }
            }
            PersonEatState.UiLabel.NoInternetConnection -> {
                hideProgressBar()
                showSnackbars(R.string.no_internet_connection)
            }
        }
    }

    private fun showProgressBar() {
        binding.flProgress.isVisible = true
    }

    private fun hideProgressBar() {
        binding.flProgress.isVisible = false
    }
}

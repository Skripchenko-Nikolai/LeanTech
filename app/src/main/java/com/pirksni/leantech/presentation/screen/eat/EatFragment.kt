package com.pirksni.leantech.presentation.screen.eat

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pirksni.leantech.R
import com.pirksni.leantech.databinding.FragmentEatBinding
import com.pirksni.leantech.extensions.launchWhenStarted
import com.pirksni.leantech.extensions.showSnackbars
import com.pirksni.leantech.extensions.unsafeLazy
import com.pirksni.leantech.presentation.adapter.person.PersonAdapter
import com.pirksni.leantech.presentation.base.BaseFragment
import kotlinx.coroutines.flow.onEach

class EatFragment : BaseFragment<EatViewModel>(R.layout.fragment_eat) {

    private val binding by viewBinding(FragmentEatBinding::bind)

    private val personAdapter by unsafeLazy {
        PersonAdapter(
            onItemClick = { personModel ->
                viewModel.onEvent(EatState.Event.OnPersonClick(personModel))
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPerson.adapter = personAdapter
        with(viewModel) {
            onEvent(EatState.Event.OnGetPersonSpreadSheet)
            stateFlow.onEach(personAdapter::submitList).launchWhenStarted(lifecycleScope)
            uiLabelFlow.onEach(::handleUiLabel).launchWhenStarted(lifecycleScope)
        }
    }

    private fun handleUiLabel(uiLabel: EatState.UiLabel) {
        when (uiLabel) {
            EatState.UiLabel.HideProgressBar ->
                hideProgressBar()
            EatState.UiLabel.ShowProgressBar ->
                showProgressBar()
            EatState.UiLabel.StartPersonScreen -> {
                // TODO сделать экран заполнения еды
            }
            EatState.UiLabel.None -> {
                // ignore
            }
            is EatState.UiLabel.ErrorInternet -> {
                hideProgressBar()
                if (uiLabel.error == null) {
                    showSnackbars(uiLabel.defaultError)
                } else {
                    showSnackbars(uiLabel.error)
                }
            }
            EatState.UiLabel.NoInternetConnection -> {
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

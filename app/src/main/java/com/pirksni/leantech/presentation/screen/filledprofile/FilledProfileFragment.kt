package com.pirksni.leantech.presentation.screen.filledprofile

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pirksni.leantech.R
import com.pirksni.leantech.databinding.FragmentFilledProfileBinding
import com.pirksni.leantech.extensions.launchWhenStarted
import com.pirksni.leantech.extensions.showSnackbars
import com.pirksni.leantech.extensions.updateVerticalPaddingEdgeToEdge
import com.pirksni.leantech.presentation.base.BaseFragment
import kotlinx.coroutines.flow.onEach

class FilledProfileFragment :
    BaseFragment<FilledProfileViewModel>(R.layout.fragment_filled_profile) {

    private val binding by viewBinding(FragmentFilledProfileBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            constraintLayout.updateVerticalPaddingEdgeToEdge()
            etName.doOnTextChanged { text, _, _, _ ->
                viewModel.onEvent(FilledProfileState.Event.OnNameChanged(text.toString()))
            }
            etSecondName.doOnTextChanged { text, _, _, _ ->
                viewModel.onEvent(FilledProfileState.Event.OnSecondNameChanged(text.toString()))
            }
            etPosition.doOnTextChanged { text, _, _, _ ->
                viewModel.onEvent(FilledProfileState.Event.OnPositionChanged(text.toString()))
            }
            btnSaveProfile.setOnClickListener {
                viewModel.onEvent(FilledProfileState.Event.OnClickSaveProfile)
            }
        }
        viewModel.stateFlow.onEach(::handleUiState).launchWhenStarted(lifecycleScope)
        viewModel.uiLabelFlow.onEach(::handleUiLabel).launchWhenStarted(lifecycleScope)
    }

    private fun handleUiState(uiState: FilledProfileState.Model) {
        when (uiState.error) {
            FilledProfileState.Model.Error.INVALID_NAME ->
                showSnackbars(R.string.invalid_name)
            FilledProfileState.Model.Error.INVALID_SECOND_NAME ->
                showSnackbars(R.string.invalid_second_name)
            FilledProfileState.Model.Error.INVALID_POSITION ->
                showSnackbars(R.string.invalid_position)
            FilledProfileState.Model.Error.NONE -> {
                // ignore
            }
        }
    }

    private fun handleUiLabel(uiLabel: FilledProfileState.UiLabel) {
        when (uiLabel) {
            FilledProfileState.UiLabel.HideProgressBar ->
                binding.flProgress.isVisible = false
            FilledProfileState.UiLabel.None -> {
                // ignore
            }
            FilledProfileState.UiLabel.ShowProgressBar ->
                binding.flProgress.isVisible = true
            FilledProfileState.UiLabel.StartMenuScreen ->
                findNavController().navigate(R.id.filled_profile_fragment_to_menu_fragment)
        }
    }
}

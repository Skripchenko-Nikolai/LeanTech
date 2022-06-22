package com.pirksni.leantech.presentation.screen.filledprofile

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pirksni.leantech.R
import com.pirksni.leantech.databinding.FragmentFilledProfileBinding
import com.pirksni.leantech.extensions.launchWhenStarted
import com.pirksni.leantech.extensions.showSnackbars
import com.pirksni.leantech.extensions.textChangeListener
import com.pirksni.leantech.extensions.setThrottledClickListener
import com.pirksni.leantech.extensions.formatDate
import com.pirksni.leantech.presentation.base.BaseFragment
import com.pirksni.leantech.presentation.util.datePicker
import kotlinx.coroutines.flow.onEach

class FilledProfileFragment :
    BaseFragment<FilledProfileViewModel>(R.layout.fragment_filled_profile) {

    private val binding by viewBinding(FragmentFilledProfileBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            tiName.textChangeListener { text ->
                viewModel.onEvent(FilledProfileState.Event.OnNameChanged(text))
            }
            tiSecondName.textChangeListener { text ->
                viewModel.onEvent(FilledProfileState.Event.OnSecondNameChanged(text))
            }
            tiPatronymic.textChangeListener { text ->
                viewModel.onEvent(FilledProfileState.Event.OnPatronymicChanged(text))
            }
            tiPosition.textChangeListener { text ->
                viewModel.onEvent(FilledProfileState.Event.OnPositionChanged(text))
            }
            tiNumberPhone.textChangeListener { text ->
                viewModel.onEvent(FilledProfileState.Event.OnNumberPhoneChanged(text))
            }
            tiNicknameTelegram.textChangeListener { text ->
                viewModel.onEvent(FilledProfileState.Event.OnNicknameTelegramChanged(text))
            }
            btnSaveProfile.setThrottledClickListener {
                viewModel.onEvent(FilledProfileState.Event.OnClickSaveProfile)
            }
            tvDateBirthday.setThrottledClickListener {
                createDatePicker()
            }
        }
        with(viewModel) {
            stateFlow.onEach(::handleUiState).launchWhenStarted(lifecycleScope)
            uiLabelFlow.onEach(::handleUiLabel).launchWhenStarted(lifecycleScope)
        }
    }

    private fun handleUiState(uiState: FilledProfileState.Model) {
        when (uiState.error) {
            FilledProfileState.Model.Error.INVALID_NAME ->
                showSnackbars(R.string.empty_field_name)
            FilledProfileState.Model.Error.INVALID_SECOND_NAME ->
                showSnackbars(R.string.empty_filed_second_name)
            FilledProfileState.Model.Error.INVALID_POSITION ->
                showSnackbars(R.string.empty_filed_position)
            FilledProfileState.Model.Error.INVALID_DAY_BIRTHDAY ->
                showSnackbars(R.string.empty_filed_day_birthday)
            FilledProfileState.Model.Error.NONE -> {
                // ignore
            }
        }
    }

    private fun handleUiLabel(uiLabel: FilledProfileState.UiLabel) {
        when (uiLabel) {
            FilledProfileState.UiLabel.HideProgressBar ->
                binding.flProgress.isVisible = false
            FilledProfileState.UiLabel.ShowProgressBar ->
                binding.flProgress.isVisible = true
            FilledProfileState.UiLabel.StartMenuScreen ->
                findNavController().navigate(R.id.filled_profile_fragment_to_menu_fragment)
            FilledProfileState.UiLabel.None -> {
                // ignore
            }
        }
    }

    private fun createDatePicker() {
        val datePicker = datePicker(R.string.day_birthday_user)
        datePicker.addOnPositiveButtonClickListener {
            binding.tvDateBirthday.text = it.formatDate()
            viewModel.onEvent(FilledProfileState.Event.OnDayBirthdayChanged(it))
        }
        datePicker.show(childFragmentManager, FilledProfileFragment::class.java.name)
    }
}

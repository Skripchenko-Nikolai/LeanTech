package com.pirksni.leantech.presentation.screen.profile

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pirksni.leantech.R
import com.pirksni.leantech.databinding.FragmentProfileBinding
import com.pirksni.leantech.domain.model.ProfileModel
import com.pirksni.leantech.extensions.formatDate
import com.pirksni.leantech.extensions.launchWhenStarted
import com.pirksni.leantech.extensions.updateVerticalPaddingEdgeToEdge
import com.pirksni.leantech.presentation.base.BaseFragment
import kotlinx.coroutines.flow.onEach

class ProfileFragment : BaseFragment<ProfileViewModel>(R.layout.fragment_profile) {

    private val binding by viewBinding(FragmentProfileBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            constraintLayout.updateVerticalPaddingEdgeToEdge()
        }
        with(viewModel) {
            stateFlow.onEach(::handleUiState).launchWhenStarted(lifecycleScope)
            uiLabelFlow.onEach(::handleUiLabel).launchWhenStarted(lifecycleScope)
        }
    }

    private fun handleUiState(profileModel: ProfileModel?) {
        with(binding) {
            tvTePosition.text =
                getString(R.string.profile_position, profileModel?.position)
            tvBirthday.text =
                getString(R.string.profile_birthday, profileModel?.birthday?.formatDate())
            tvFullName.text =
                getString(
                    R.string.profile_full_name,
                    profileModel?.secondName,
                    profileModel?.name,
                    profileModel?.patronymic
                )
            setPhoneNumber(profileModel)
            tvTelegram.text =
                getString(
                    R.string.profile_telegram,
                    profileModel?.telegramNickname ?: R.string.no_telegram
                )
        }
    }

    private fun setPhoneNumber(profileModel: ProfileModel?) {
        with(binding) {
            if (profileModel == null) {
                tvPhoneNumber.text = getText(R.string.no_number)
            } else {
                tvPhoneNumber.text =
                    getString(
                        R.string.profile_phone_number,
                        "+${profileModel.phoneNumber}"
                    )
            }
        }
    }

    private fun handleUiLabel(uiLabel: ProfileState.UiLabel) {
        when (uiLabel) {
            ProfileState.UiLabel.HideProgressBar ->
                binding.flProgress.isVisible = false
            ProfileState.UiLabel.None -> {
                // ignore
            }
            ProfileState.UiLabel.ShowProgressBar ->
                binding.flProgress.isVisible = true

        }
    }
}

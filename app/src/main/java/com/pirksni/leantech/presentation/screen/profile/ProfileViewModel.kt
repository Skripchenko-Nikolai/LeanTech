package com.pirksni.leantech.presentation.screen.profile

import com.pirksni.leantech.presentation.base.BaseViewModel
import com.pirksni.leantech.presentation.interactor.ProfileInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val profileInteractor: ProfileInteractor
) : BaseViewModel() {

    private val _stateFlow = MutableStateFlow(profileInteractor.getProfile())
    val stateFlow = _stateFlow.asStateFlow()

    private val _uiLabelFlow =
        MutableStateFlow<ProfileState.UiLabel>(ProfileState.UiLabel.None)
    val uiLabelFlow = _uiLabelFlow.asStateFlow()

    fun onEvent(event: ProfileState.Event) {
        val model = _stateFlow.value
        when (event) {
            ProfileState.Event.OnPhoneNumberClick ->
                _uiLabelFlow.value = ProfileState.UiLabel.CallPhone(model?.phoneNumber)
        }
    }
}

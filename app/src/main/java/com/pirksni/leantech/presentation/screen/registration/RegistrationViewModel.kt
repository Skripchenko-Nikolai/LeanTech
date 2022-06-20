package com.pirksni.leantech.presentation.screen.registration

import com.pirksni.leantech.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class RegistrationViewModel @Inject constructor() : BaseViewModel() {

    private val _uiLabelFlow =
        MutableStateFlow<RegistrationState.UiLabel>(RegistrationState.UiLabel.None)
    val uiLabelFlow = _uiLabelFlow.asStateFlow()

    fun onEvent(event: RegistrationState.Event) {
        when (event) {
            RegistrationState.Event.OnStartNextScreen ->
                chekFilledProfile()
        }
    }

    private fun chekFilledProfile() {
        // TODO implementation надо будет проверять под каким email заходил человек и заполнял ли он профиль
        _uiLabelFlow.value = RegistrationState.UiLabel.OnStartFilledProfileScreen
    }
}

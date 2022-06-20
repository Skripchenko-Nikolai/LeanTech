package com.pirksni.leantech.presentation.screen.registration

import com.pirksni.leantech.presentation.base.BaseViewModel
import com.pirksni.leantech.presentation.interactor.ProfileInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val profileInteractor: ProfileInteractor
) : BaseViewModel() {

    private val _uiLabelFlow =
        MutableStateFlow<RegistrationState.UiLabel>(RegistrationState.UiLabel.None)
    val uiLabelFlow = _uiLabelFlow.asStateFlow()

    private var model = RegistrationState.Model.DEFAULT

    fun onEvent(event: RegistrationState.Event) {
        when (event) {
            RegistrationState.Event.OnStartNextScreen ->
                chekFilledProfile()
            is RegistrationState.Event.OnChangeEmail ->
                model = model.copy(email = event.email)
        }
    }

    private fun chekFilledProfile() {
        val profileModel = profileInteractor.getProfile()
        if (profileModel == null) {
            profileInteractor.saveEmail(model.email)
            _uiLabelFlow.value = RegistrationState.UiLabel.OnStartFilledProfileScreen
        } else {
            checkEmail()
        }
    }

    private fun checkEmail() {
        _uiLabelFlow.value = RegistrationState.UiLabel.OnStartMenuScreen

    }
}

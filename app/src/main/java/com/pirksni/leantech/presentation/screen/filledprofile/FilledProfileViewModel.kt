package com.pirksni.leantech.presentation.screen.filledprofile

import com.pirksni.leantech.presentation.base.BaseViewModel
import com.pirksni.leantech.presentation.interactor.ProfileInteractor
import com.pirksni.leantech.presentation.screen.filledprofile.FilledProfileState.Model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class FilledProfileViewModel @Inject constructor(
    private val profileInteractor: ProfileInteractor,
) : BaseViewModel() {

    private val _stateFlow = MutableStateFlow(FilledProfileState.Model.DEFAULT)
    val stateFlow = _stateFlow.asStateFlow()

    private val _uiLabelFlow =
        MutableStateFlow<FilledProfileState.UiLabel>(FilledProfileState.UiLabel.None)
    val uiLabelFlow = _uiLabelFlow.asStateFlow()

    fun onEvent(event: FilledProfileState.Event) {
        changeStateErrorOnNone()
        val model = _stateFlow.value
        when (event) {
            FilledProfileState.Event.OnClickSaveProfile -> validateState()
            is FilledProfileState.Event.OnNameChanged ->
                _stateFlow.value = model.copy(name = event.name)
            is FilledProfileState.Event.OnPositionChanged ->
                _stateFlow.value = model.copy(position = event.position)
            is FilledProfileState.Event.OnSecondNameChanged ->
                _stateFlow.value = model.copy(secondName = event.secondName)
        }
    }

    private fun changeStateErrorOnNone() {
        _stateFlow.value = _stateFlow.value.copy(error = Error.NONE)
    }

    private fun validateState() {
        _uiLabelFlow.value = FilledProfileState.UiLabel.ShowProgressBar
        val model = _stateFlow.value
        when {
            model.name.isEmpty() -> {
                _uiLabelFlow.value = FilledProfileState.UiLabel.HideProgressBar
                _stateFlow.value = model.copy(error = Error.INVALID_NAME)
            }
            model.secondName.isEmpty() -> {
                _uiLabelFlow.value = FilledProfileState.UiLabel.HideProgressBar
                _stateFlow.value = model.copy(error = Error.INVALID_SECOND_NAME)
            }
            model.position.isEmpty() -> {
                _uiLabelFlow.value = FilledProfileState.UiLabel.HideProgressBar
                _stateFlow.value = model.copy(error = Error.INVALID_POSITION)
            }
            else -> saveProfile()
        }
    }

    private fun saveProfile() {
        profileInteractor.saveProfile(_stateFlow.value)
        _uiLabelFlow.value = FilledProfileState.UiLabel.StartMenuScreen
    }

    init {
        val profileModel = profileInteractor.getProfile()
        _stateFlow.value = _stateFlow.value.copy(email = profileModel?.email ?: NO_EMAIL)
    }

    companion object {
        private const val NO_EMAIL = "Email отсутсвует"
    }
}

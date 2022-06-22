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
            is FilledProfileState.Event.OnDayBirthdayChanged ->
                _stateFlow.value = model.copy(dayBirthday = event.dayBirthday)
            is FilledProfileState.Event.OnNicknameTelegramChanged ->
                _stateFlow.value = model.copy(nicknameTelegram = event.nicknameTelegram)
            is FilledProfileState.Event.OnNumberPhoneChanged ->
                _stateFlow.value = model.copy(numberPhone = event.numberPhone)
            is FilledProfileState.Event.OnPatronymicChanged ->
                _stateFlow.value = model.copy(patronymic = event.patronymic)
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
            model.patronymic.isEmpty() -> {
                _uiLabelFlow.value = FilledProfileState.UiLabel.HideProgressBar
                _stateFlow.value = model.copy(error = Error.INVALID_PATRONYMIC)
            }
            model.dayBirthday == 0L -> {
                _uiLabelFlow.value = FilledProfileState.UiLabel.HideProgressBar
                _stateFlow.value = model.copy(error = Error.INVALID_DAY_BIRTHDAY)
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
        _stateFlow.value = _stateFlow.value.copy(email = profileInteractor.getEmail())
    }
}

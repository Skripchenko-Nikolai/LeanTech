package com.pirksni.leantech.presentation.screen.eat

import androidx.lifecycle.viewModelScope
import com.pirksni.leantech.domain.model.PersonModel
import com.pirksni.leantech.presentation.base.BaseViewModel
import com.pirksni.leantech.presentation.interactor.PersonInteractor
import com.pirksni.leantech.presentation.util.network.ResultWrapper
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class EatViewModel @Inject constructor(
    private val personInteractor: PersonInteractor
) : BaseViewModel() {

    private val _stateFlow = MutableSharedFlow<List<PersonModel>>()
    val stateFlow = _stateFlow.asSharedFlow()

    private val _uiLabelFlow =
        MutableStateFlow<EatState.UiLabel>(EatState.UiLabel.None)
    val uiLabelFlow = _uiLabelFlow.asStateFlow()

    fun onEvent(event: EatState.Event) {
        when (event) {
            EatState.Event.OnGetPersonSpreadSheet ->
                getPerson()
            is EatState.Event.OnPersonClick -> {
                // TODO implemented
            }
        }
    }

    private fun getPerson() {
        viewModelScope.launch {
            _uiLabelFlow.value = EatState.UiLabel.ShowProgressBar
            when (val response = personInteractor.getPerson()) {
                is ResultWrapper.GenericError ->
                    _uiLabelFlow.value = EatState.UiLabel.ErrorInternet(
                        error = response.error?.msg
                    )
                ResultWrapper.NetworkError -> {
                    _uiLabelFlow.value = EatState.UiLabel.NoInternetConnection
                }
                is ResultWrapper.Success -> {
                    _stateFlow.emit(response.value)
                    _uiLabelFlow.value = EatState.UiLabel.HideProgressBar
                }
            }
        }
    }
}

package com.pirksni.leantech.presentation.screen.personeat

import androidx.lifecycle.viewModelScope
import com.pirksni.leantech.domain.model.PersonEatModel
import com.pirksni.leantech.presentation.base.BaseViewModel
import com.pirksni.leantech.presentation.interactor.PersonInteractor
import com.pirksni.leantech.presentation.util.network.ResultWrapper
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PersonEatViewModel @Inject constructor(
    private val personInteractor: PersonInteractor,
) : BaseViewModel() {

    private val _stateFlow = MutableSharedFlow<List<PersonEatModel>>()
    val stateFlow = _stateFlow.asSharedFlow()

    private val _uiLabelFlow =
        MutableStateFlow<PersonEatState.UiLabel>(PersonEatState.UiLabel.None)
    val uiLabelFlow = _uiLabelFlow.asStateFlow()

    fun onEvent(event: PersonEatState.Event) {
        when (event) {
            is PersonEatState.Event.OnEatChange -> {
                // TODO
            }
            PersonEatState.Event.OnGetPersonEatSpreadSheet ->
                getPersonEat()
        }
    }

    private fun getPersonEat() {
        viewModelScope.launch {
            _uiLabelFlow.value = PersonEatState.UiLabel.ShowProgressBar
            when (val response = personInteractor.getPersonEat()) {
                is ResultWrapper.GenericError ->
                    _uiLabelFlow.value = PersonEatState.UiLabel.ErrorInternet(
                        error = response.error?.msg
                    )
                ResultWrapper.NetworkError -> {
                    _uiLabelFlow.value = PersonEatState.UiLabel.NoInternetConnection
                }
                is ResultWrapper.Success -> {
                    _stateFlow.emit(response.value)
                    _uiLabelFlow.value = PersonEatState.UiLabel.HideProgressBar
                }
            }
        }
    }
}

package com.pirksni.leantech.presentation.screen.personeat

import androidx.lifecycle.viewModelScope
import com.pirksni.leantech.extensions.mapToLists
import com.pirksni.leantech.presentation.base.BaseViewModel
import com.pirksni.leantech.presentation.interactor.PersonInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PersonEatViewModel @Inject constructor(
    private val personInteractor: PersonInteractor,
) : BaseViewModel() {

    private val _stateFlow = MutableSharedFlow<List<String>>()
    val stateFlow = _stateFlow.asSharedFlow()

    private val _uiLabelFlow =
        MutableStateFlow<PersonEatState.UiLabel>(PersonEatState.UiLabel.None)
    val uiLabelFlow = _uiLabelFlow.asStateFlow()

    private val changeEats = mutableMapOf<String, String>()

    fun onEvent(event: PersonEatState.Event) {
        when (event) {
            is PersonEatState.Event.OnEatChange ->
                changeEats[event.eat] = event.count
            PersonEatState.Event.OnGetPersonEatSpreadSheet ->
                getPersonEat()
            is PersonEatState.Event.OnSaveEat ->
                saveEat(event.positionPerson)
        }
    }

    private fun saveEat(positionPerson: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiLabelFlow.value = PersonEatState.UiLabel.ShowProgressBar
            val position = positionPerson.toInt() + NUMBER_FOR_RANGE
            val range = "$START_CHAR_EAT_RANGE$position$END_CHAR_EAT_RANGE$position"
            personInteractor.updateEat(range, changeEats.mapToLists())
            _uiLabelFlow.value = PersonEatState.UiLabel.ExitScreen
        }
    }

    private fun getPersonEat() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiLabelFlow.value = PersonEatState.UiLabel.ShowProgressBar
            val eats = personInteractor.getEats()
            _stateFlow.emit(eats.orEmpty())
            initChangeEats(eats.orEmpty())
            _uiLabelFlow.value = PersonEatState.UiLabel.HideProgressBar
        }
    }

    private fun initChangeEats(eats: List<String>) {
        eats.forEach {
            changeEats[it] = INITIAL_VALUE_COUNT_EATS
        }
    }

    companion object {
        private const val INITIAL_VALUE_COUNT_EATS = "0"
        private const val NUMBER_FOR_RANGE = 1
        private const val START_CHAR_EAT_RANGE = "C"
        private const val END_CHAR_EAT_RANGE = ":Q"
    }
}

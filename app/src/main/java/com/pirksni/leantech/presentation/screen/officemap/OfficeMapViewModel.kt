package com.pirksni.leantech.presentation.screen.officemap

import androidx.lifecycle.viewModelScope
import com.pirksni.leantech.data.firebase.FirebaseState
import com.pirksni.leantech.domain.model.PointModel
import com.pirksni.leantech.presentation.base.BaseViewModel
import com.pirksni.leantech.presentation.interactor.PointMapInteractor
import com.pirksni.leantech.presentation.interactor.ProfileInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class OfficeMapViewModel @Inject constructor(
    private val pointMapInteractor: PointMapInteractor,
    private val profileInteractor: ProfileInteractor
) : BaseViewModel() {

    private val _pointFlow = MutableSharedFlow<List<PointModel>>()
    val pointFlow = _pointFlow.asSharedFlow()

    private val _stateFlow = MutableStateFlow(OfficeMapState.Model.DEFAULT)
    val stateFlow = _stateFlow.asSharedFlow()

    private val _uiLabelFlow =
        MutableStateFlow<OfficeMapState.UiLabel>(OfficeMapState.UiLabel.None)
    val uiLabelFlow = _uiLabelFlow.asStateFlow()

    fun onEvent(event: OfficeMapState.Event) {
        setNoneError()
        when (event) {
            OfficeMapState.Event.OnGetPoint ->
                getPoint()
            OfficeMapState.Event.OnSetPoint ->
                validModel()
            is OfficeMapState.Event.OnPointChange ->
                _stateFlow.value = _stateFlow.value.copy(
                    coordinateX = event.x,
                    coordinateY = event.y
                )
        }
    }

    private fun getPoint() {
        viewModelScope.launch(Dispatchers.IO) {
            _pointFlow.emit(pointMapInteractor.getPoint())
        }
    }

    private fun validModel() {
        val model = _stateFlow.value
        when {
            model.email.isEmpty() || model.name.isEmpty() || model.secondName.isEmpty() ->
                _stateFlow.value = model.copy(error = OfficeMapState.Model.Error.NO_INFO_PERSON)
            model.coordinateX == 0.0 || model.coordinateY == 0.0 ->
                _stateFlow.value =
                    model.copy(error = OfficeMapState.Model.Error.NO_COORDINATE_POINT)
            else -> setPoint()
        }
    }

    private fun setPoint() {
        viewModelScope.launch(Dispatchers.IO) {
            when (pointMapInteractor.setPointValue(_stateFlow.value)) {
                FirebaseState.SUCCESS -> TODO()
                FirebaseState.FAILURE -> TODO()
                FirebaseState.DEFAULT -> {
                    // ignore
                }
            }
        }
    }

    private fun getPersonInfo() {
        val profile = profileInteractor.getProfile()
        val email = profileInteractor.getEmail()
        profile?.let { profileModel ->
            _stateFlow.value = _stateFlow.value.copy(
                email = email,
                name = profileModel.name,
                secondName = profileModel.secondName
            )
        }
    }

    private fun setNoneError() {
        _stateFlow.value = _stateFlow.value.copy(error = OfficeMapState.Model.Error.NONE)
    }

    init {
        getPersonInfo()
    }
}

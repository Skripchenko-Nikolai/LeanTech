package com.pirksni.leantech.presentation.screen.eat

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.pirksni.leantech.domain.repository.PersonNetworkRepository
import com.pirksni.leantech.presentation.base.BaseViewModel
import com.pirksni.leantech.presentation.screen.filledprofile.FilledProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class EatViewModel @Inject constructor(
    private val personNetworkRepository: PersonNetworkRepository
) : BaseViewModel() {

    private val _uiLabelFlow =
        MutableStateFlow<EatState.UiLabel>(EatState.UiLabel.None)
    val uiLabelFlow = _uiLabelFlow.asStateFlow()

    private val eats: MutableMap<String, Int> = mutableMapOf()

    fun onEvent(event: EatState.Event) {
        when (event) {
            is EatState.Event.OnEatAdd ->
                addEat(event.eatsName, event.count)
            EatState.Event.OnFillGoogleSpreadSheet -> TODO()
            EatState.Event.OnGetGoogleSpreadSheet -> TODO()
        }
    }

    private fun addEat(key: String, value: Int) {
        if (value == ZERO_COUNT) {
            eats[key] = value
        } else {
            eats.remove(key)
        }
    }

    fun test() {
        Log.e("qwe", "qwe")
        viewModelScope.launch {
            Log.e("qwe", "${personNetworkRepository.putPersons()}")
        }
    }

    companion object {
        private const val ZERO_COUNT = 0
    }
}

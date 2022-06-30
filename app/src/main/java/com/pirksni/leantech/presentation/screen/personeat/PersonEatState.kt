package com.pirksni.leantech.presentation.screen.personeat

import com.pirksni.leantech.R
import com.pirksni.leantech.domain.model.PersonModel

interface PersonEatState {

    sealed class Event {
        object OnGetPersonEatSpreadSheet : Event()
        data class OnEatChange(val personModel: PersonModel) : Event()
    }

    sealed class UiLabel {
        object ShowProgressBar : UiLabel()
        object HideProgressBar : UiLabel()
        object NoInternetConnection : UiLabel()
        data class ErrorInternet(
            val error: String? = null,
            val defaultError: Int = R.string.unknown_error
        ) : UiLabel()
        object None : UiLabel()
        object OnExitScreen : UiLabel()
    }
}

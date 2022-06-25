package com.pirksni.leantech.presentation.screen.eat

import com.pirksni.leantech.domain.model.PersonModel

interface EatState {

    sealed class Event {
        object OnGetPersonSpreadSheet : Event()
        data class OnPersonClick(val personModel: PersonModel) : Event()
    }

    sealed class UiLabel {
        object ShowProgressBar : UiLabel()
        object HideProgressBar : UiLabel()
        object StartPersonScreen : UiLabel()
        object None : UiLabel()
    }
}

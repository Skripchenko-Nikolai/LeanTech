package com.pirksni.leantech.presentation.screen.personeat

interface PersonEatState {

    sealed class Event {
        object OnGetPersonEatSpreadSheet : Event()
        data class OnSaveEat(val positionPerson: String) : Event()
        data class OnEatChange(val eat: String, val count: String) : Event()
    }

    sealed class UiLabel {
        object ShowProgressBar : UiLabel()
        object HideProgressBar : UiLabel()
        object ExitScreen : UiLabel()
        object None : UiLabel()
    }
}

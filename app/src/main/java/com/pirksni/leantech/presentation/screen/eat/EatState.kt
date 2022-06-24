package com.pirksni.leantech.presentation.screen.eat

interface EatState {

    sealed class Event {
        data class OnEatAdd(val eatsName: String, val count: Int) : Event()
        object OnFillGoogleSpreadSheet : Event()
        object OnGetGoogleSpreadSheet : Event()
    }

    sealed class UiLabel {
        object ShowProgressBar : UiLabel()
        object HideProgressBar : UiLabel()
        object None : UiLabel()
    }
}

package com.pirksni.leantech.presentation.screen.profile

interface ProfileState {

    sealed class Event {
        object OnPhoneNumberClick : Event()
    }

    sealed class UiLabel {
        object ShowProgressBar : UiLabel()
        object HideProgressBar : UiLabel()
        object None : UiLabel()
        data class CallPhone(val phoneNumber: String?) : UiLabel()
    }
}

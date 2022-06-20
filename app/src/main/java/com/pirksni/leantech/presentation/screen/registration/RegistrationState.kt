package com.pirksni.leantech.presentation.screen.registration

interface RegistrationState {

    sealed class Event {
        object OnStartNextScreen : Event()
    }

    sealed class UiLabel {
        object OnStartFilledProfileScreen : UiLabel()
        object OnStartMenuScreen : UiLabel()
        object None : UiLabel()
    }
}

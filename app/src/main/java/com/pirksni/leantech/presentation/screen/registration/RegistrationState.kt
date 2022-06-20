package com.pirksni.leantech.presentation.screen.registration

interface RegistrationState {

    data class Model(
        val email: String
    ) {
        companion object {
            val DEFAULT = Model(
                email = ""
            )
        }
    }

    sealed class Event {
        object OnStartNextScreen : Event()
        data class OnChangeEmail(val email: String) : Event()
    }

    sealed class UiLabel {
        object OnStartFilledProfileScreen : UiLabel()
        object OnStartMenuScreen : UiLabel()
        object None : UiLabel()
    }
}

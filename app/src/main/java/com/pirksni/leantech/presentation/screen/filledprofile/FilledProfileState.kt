package com.pirksni.leantech.presentation.screen.filledprofile

interface FilledProfileState {

    data class Model(
        val email: String,
        val name: String,
        val secondName: String,
        val position: String,
        val error: Error
    ) {
        enum class Error {
            INVALID_NAME,
            INVALID_SECOND_NAME,
            INVALID_POSITION,
            NONE
        }

        companion object {
            val DEFAULT = Model(
                email = "",
                name = "",
                secondName = "",
                position = "",
                error = Error.NONE
            )
        }
    }

    sealed class Event {
        data class OnNameChanged(val name: String) : Event()
        data class OnSecondNameChanged(val secondName: String) : Event()
        data class OnPositionChanged(val position: String) : Event()
        object OnClickSaveProfile : Event()
    }

    sealed class UiLabel {
        object ShowProgressBar : UiLabel()
        object HideProgressBar : UiLabel()
        object StartMenuScreen : UiLabel()
        object None : UiLabel()
    }
}

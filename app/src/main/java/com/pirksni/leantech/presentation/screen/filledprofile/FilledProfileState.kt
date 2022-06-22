package com.pirksni.leantech.presentation.screen.filledprofile

interface FilledProfileState {

    data class Model(
        val email: String,
        val name: String,
        val secondName: String,
        val patronymic: String,
        val dayBirthday: Long,
        val position: String,
        val numberPhone: String,
        val nicknameTelegram: String,
        val error: Error
    ) {
        enum class Error {
            INVALID_NAME,
            INVALID_SECOND_NAME,
            INVALID_DAY_BIRTHDAY,
            INVALID_POSITION,
            NONE
        }

        companion object {
            val DEFAULT = Model(
                email = "",
                name = "",
                secondName = "",
                patronymic = "",
                dayBirthday = 0L,
                position = "",
                numberPhone = "",
                nicknameTelegram = "",
                error = Error.NONE
            )
        }
    }

    sealed class Event {
        data class OnNameChanged(val name: String) : Event()
        data class OnSecondNameChanged(val secondName: String) : Event()
        data class OnPatronymicChanged(val patronymic: String) : Event()
        data class OnDayBirthdayChanged(val dayBirthday: Long) : Event()
        data class OnPositionChanged(val position: String) : Event()
        data class OnNumberPhoneChanged(val numberPhone: String) : Event()
        data class OnNicknameTelegramChanged(val nicknameTelegram: String) : Event()
        object OnClickSaveProfile : Event()
    }

    sealed class UiLabel {
        object ShowProgressBar : UiLabel()
        object HideProgressBar : UiLabel()
        object StartMenuScreen : UiLabel()
        object None : UiLabel()
    }
}

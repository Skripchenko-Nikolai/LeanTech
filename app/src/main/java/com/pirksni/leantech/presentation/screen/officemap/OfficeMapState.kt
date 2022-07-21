package com.pirksni.leantech.presentation.screen.officemap

interface OfficeMapState {

    data class Model(
        val email: String,
        val name: String,
        val secondName: String,
        val coordinateX: Double,
        val coordinateY: Double,
        val error: Error
    ) {
        enum class Error {
            ERROR_SAVE_POINT,
            ERROR_GET_POINT,
            NO_INFO_PERSON,
            NO_COORDINATE_POINT,
            NONE
        }

        companion object {
            val DEFAULT = Model(
                email = "",
                name = "",
                secondName = "",
                coordinateX = 0.0,
                coordinateY = 0.0,
                error = Error.NONE
            )
        }
    }

    sealed class Event {
        data class OnPointChange(val x: Double, val y: Double) : Event()
        object OnGetPoint : Event()
        object OnSetPoint : Event()
    }

    sealed class UiLabel {
        object ShowProgressBar : UiLabel()
        object HideProgressBar : UiLabel()
        data class ShowSnackbarSetPoint(val message: Int) : UiLabel()
        object None : UiLabel()
    }
}

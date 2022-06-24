package com.pirksni.leantech.presentation.screen.profile

interface ProfileState {

    sealed class UiLabel {
        object ShowProgressBar : UiLabel()
        object HideProgressBar : UiLabel()
        object None : UiLabel()
    }
}

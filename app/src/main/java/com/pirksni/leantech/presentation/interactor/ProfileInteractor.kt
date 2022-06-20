package com.pirksni.leantech.presentation.interactor

import com.pirksni.leantech.domain.model.ProfileModel
import com.pirksni.leantech.presentation.screen.filledprofile.FilledProfileState

interface ProfileInteractor {

    fun saveEmail(email: String)
    fun saveProfile(model: FilledProfileState.Model)
    fun getProfile(): ProfileModel?
}

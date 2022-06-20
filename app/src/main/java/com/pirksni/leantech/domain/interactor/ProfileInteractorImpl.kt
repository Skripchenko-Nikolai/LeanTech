package com.pirksni.leantech.domain.interactor

import com.pirksni.leantech.domain.model.ProfileModel
import com.pirksni.leantech.domain.repository.ProfilePreferencesRepository
import com.pirksni.leantech.extensions.mapToProfileModel
import com.pirksni.leantech.presentation.interactor.ProfileInteractor
import com.pirksni.leantech.presentation.screen.filledprofile.FilledProfileState
import javax.inject.Inject

class ProfileInteractorImpl @Inject constructor(
    private val profilePreferencesRepository: ProfilePreferencesRepository
) : ProfileInteractor {

    override fun saveEmail(email: String) {
        profilePreferencesRepository.saveProfile(email.mapToProfileModel())
    }

    override fun saveProfile(model: FilledProfileState.Model) {
        profilePreferencesRepository.saveProfile(model.mapToProfileModel())
    }

    override fun getProfile(): ProfileModel? =
        profilePreferencesRepository.getProfile()
}

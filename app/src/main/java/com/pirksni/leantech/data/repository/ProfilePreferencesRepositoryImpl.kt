package com.pirksni.leantech.data.repository

import com.pirksni.leantech.data.preferences.ProfileSharedPreferences
import com.pirksni.leantech.domain.model.ProfileModel
import com.pirksni.leantech.domain.repository.ProfilePreferencesRepository
import javax.inject.Inject

class ProfilePreferencesRepositoryImpl @Inject constructor(
    private val profileSharedPreferences: ProfileSharedPreferences
) : ProfilePreferencesRepository {

    override fun saveProfile(profileModel: ProfileModel) {
        profileSharedPreferences.profile = profileModel
    }

    override fun getProfile(): ProfileModel? =
        profileSharedPreferences.profile
}

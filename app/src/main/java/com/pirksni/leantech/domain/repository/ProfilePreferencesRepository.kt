package com.pirksni.leantech.domain.repository

import com.pirksni.leantech.domain.model.ProfileModel

interface ProfilePreferencesRepository {

    fun saveProfile(profileModel: ProfileModel)
    fun getProfile(): ProfileModel?
}

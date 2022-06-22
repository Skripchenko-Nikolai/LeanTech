package com.pirksni.leantech.domain.repository

import com.pirksni.leantech.domain.model.ProfileModel

interface ProfilePreferencesRepository {

    fun saveEmail(email: String)
    fun getEmail(): String
    fun saveProfile(profileModel: ProfileModel)
    fun getProfile(): ProfileModel?
}

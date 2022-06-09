package com.pirksni.leantech.data.preferences

import android.content.SharedPreferences
import com.google.gson.Gson
import com.pirksni.leantech.domain.model.ProfileModel
import javax.inject.Inject

class ProfileSharedPreferences @Inject constructor(
    private val preferences: SharedPreferences
) {
    var profile: ProfileModel
        get() {
            val stringVal = preferences.getString(PROFILE, "")
            return Gson().fromJson(stringVal, ProfileModel::class.java)
        }
        set(value) {
            val stringVal = Gson().toJson(value)
            preferences.edit().putString(PROFILE, stringVal).apply()
        }


    companion object {
        private const val PROFILE = "PROFILE"
    }
}

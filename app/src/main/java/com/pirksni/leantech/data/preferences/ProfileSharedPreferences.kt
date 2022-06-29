package com.pirksni.leantech.data.preferences

import android.content.SharedPreferences
import com.pirksni.leantech.domain.model.ProfileModel
import com.squareup.moshi.Moshi
import javax.inject.Inject

class ProfileSharedPreferences @Inject constructor(
    private val preferences: SharedPreferences,
    moshi: Moshi,
) {
    private val jsonAdapter = moshi.adapter(ProfileModel::class.java)

    var profile: ProfileModel?
        get() {
            val stringVal = preferences.getString(PROFILE, null)
            return stringVal?.let {
                jsonAdapter.fromJson(it)
            }
        }
        set(value) {
            val stringVal = jsonAdapter.toJson(value)
            preferences.edit().putString(PROFILE, stringVal).apply()
        }

    // !! потому что во время авторизации я сохраняю и потом она null не может быть
    var email: String
        get() = preferences.getString(EMAIL, "")!!
        set(value) = preferences.edit().putString(EMAIL, value).apply()


    companion object {
        private const val PROFILE = "PROFILE"
        private const val EMAIL = "EMAIL"
    }
}

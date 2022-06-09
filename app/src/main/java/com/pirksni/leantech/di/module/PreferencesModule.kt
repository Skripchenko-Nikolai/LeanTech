package com.pirksni.leantech.di.module

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.pirksni.leantech.data.preferences.ProfileSharedPreferences
import dagger.Module
import dagger.Provides

@Module
class PreferencesModule {

    @Provides
    fun providePreferences(context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    fun provideProfilePreferences(preference: SharedPreferences): ProfileSharedPreferences =
        ProfileSharedPreferences(preference)
}

package com.pirksni.leantech.di.module

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FirebaseModule {

    @Singleton
    @Provides
    fun providesAnalytics(context: Context): FirebaseAnalytics =
        FirebaseAnalytics.getInstance(context)
}

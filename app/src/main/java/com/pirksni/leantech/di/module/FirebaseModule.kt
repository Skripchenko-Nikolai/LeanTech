package com.pirksni.leantech.di.module

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.firestore.FirebaseFirestore
import com.pirksni.leantech.data.firebase.FirebasePointMap
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FirebaseModule {

    @Singleton
    @Provides
    fun providesAnalytics(context: Context): FirebaseAnalytics =
        FirebaseAnalytics.getInstance(context)

    @Singleton
    @Provides
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    fun provideFirebaseSetValue(fireStoreFirebase: FirebaseFirestore) =
        FirebasePointMap(fireStoreFirebase)
}

package com.pirksni.leantech.di.module

import android.content.Context
import com.pirksni.leantech.data.google.GetValueSheet
import com.pirksni.leantech.data.google.UpdateValuesSheet
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides

@Module
class GoogleSheetModule {

    @Provides
    fun provideGetValueSheet(context: Context, moshi: Moshi) =
        GetValueSheet(context, moshi)

    @Provides
    fun provideUpdateValuesSheet(context: Context, moshi: Moshi) =
        UpdateValuesSheet(context, moshi)
}

package com.pirksni.leantech.di.module

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MoshiModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()
}

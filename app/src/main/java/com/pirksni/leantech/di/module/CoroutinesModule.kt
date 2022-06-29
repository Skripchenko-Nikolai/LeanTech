package com.pirksni.leantech.di.module

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class CoroutinesModule {

    @Provides
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO
}

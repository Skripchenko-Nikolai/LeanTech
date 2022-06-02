package com.pirksni.leantech.di.module

import com.pirksni.leantech.presentation.screen.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity
}

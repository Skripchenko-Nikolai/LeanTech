package com.pirksni.leantech.di.module

import com.pirksni.leantech.presentation.screen.menu.MenuFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {
    @ContributesAndroidInjector
    fun provideMenuFragment(): MenuFragment
}

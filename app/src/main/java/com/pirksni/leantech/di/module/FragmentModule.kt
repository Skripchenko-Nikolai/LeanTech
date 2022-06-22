package com.pirksni.leantech.di.module

import com.pirksni.leantech.presentation.screen.filledprofile.FilledProfileFragment
import com.pirksni.leantech.presentation.screen.menu.MenuFragment
import com.pirksni.leantech.presentation.screen.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    fun provideFilledProfileFragment(): FilledProfileFragment

    @ContributesAndroidInjector
    fun provideMenuFragment(): MenuFragment

    @ContributesAndroidInjector
    fun provideProfileFragment(): ProfileFragment
}

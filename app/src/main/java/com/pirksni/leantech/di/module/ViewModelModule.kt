package com.pirksni.leantech.di.module

import androidx.lifecycle.ViewModel
import com.pirksni.leantech.di.ViewModelKey
import com.pirksni.leantech.presentation.screen.filledprofile.FilledProfileViewModel
import com.pirksni.leantech.presentation.screen.main.MainViewModel
import com.pirksni.leantech.presentation.screen.menu.MenuViewModel
import com.pirksni.leantech.presentation.screen.profile.ProfileViewModel
import com.pirksni.leantech.presentation.screen.registration.RegistrationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    abstract fun bindRegistrationViewModel(viewModel: RegistrationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FilledProfileViewModel::class)
    abstract fun bindFilledProfileViewModel(viewModel: FilledProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MenuViewModel::class)
    abstract fun bindMenuViewModel(viewModel: MenuViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel
}

package com.pirksni.leantech.di.module

import androidx.lifecycle.ViewModel
import com.pirksni.leantech.di.ViewModelKey
import com.pirksni.leantech.presentation.screen.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}

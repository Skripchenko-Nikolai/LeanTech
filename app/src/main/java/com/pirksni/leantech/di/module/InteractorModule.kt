package com.pirksni.leantech.di.module

import com.pirksni.leantech.domain.interactor.ProfileInteractorImpl
import com.pirksni.leantech.presentation.interactor.ProfileInteractor
import dagger.Binds
import dagger.Module

@Module
abstract class InteractorModule {

    @Binds
    abstract fun provideProfileInteracotr(interactor: ProfileInteractorImpl): ProfileInteractor
}

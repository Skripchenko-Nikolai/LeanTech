package com.pirksni.leantech.di.module

import com.pirksni.leantech.domain.interactor.PersonInteractorImpl
import com.pirksni.leantech.domain.interactor.PointMapInteractorImpl
import com.pirksni.leantech.domain.interactor.ProfileInteractorImpl
import com.pirksni.leantech.presentation.interactor.PersonInteractor
import com.pirksni.leantech.presentation.interactor.PointMapInteractor
import com.pirksni.leantech.presentation.interactor.ProfileInteractor
import dagger.Binds
import dagger.Module

@Module
abstract class InteractorModule {

    @Binds
    abstract fun bindsProfileInteracotr(interactor: ProfileInteractorImpl): ProfileInteractor

    @Binds
    abstract fun bindsPersonInteractor(interactor: PersonInteractorImpl): PersonInteractor

    @Binds
    abstract fun bindsPointMapInteractor(interactor: PointMapInteractorImpl): PointMapInteractor
}

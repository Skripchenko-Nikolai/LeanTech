package com.pirksni.leantech.di.module

import com.pirksni.leantech.data.repository.ProfilePreferencesRepositoryImpl
import com.pirksni.leantech.domain.repository.ProfilePreferencesRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryPreferencesModule {

    @Binds
    abstract fun bindsProfilePreferencesRepository(
        repository: ProfilePreferencesRepositoryImpl
    ): ProfilePreferencesRepository
}

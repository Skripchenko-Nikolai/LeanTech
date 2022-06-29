package com.pirksni.leantech.di.module

import com.pirksni.leantech.data.repository.PersonNetworkRepositoryImpl
import com.pirksni.leantech.domain.repository.PersonNetworkRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryNetworkModule {

    @Binds
    abstract fun bindsPersonNetworkRepository(
        repository: PersonNetworkRepositoryImpl
    ): PersonNetworkRepository
}

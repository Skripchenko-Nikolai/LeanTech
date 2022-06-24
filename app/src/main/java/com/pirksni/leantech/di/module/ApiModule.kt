package com.pirksni.leantech.di.module

import com.pirksni.leantech.data.api.LeanTechServiceApi
import com.pirksni.leantech.data.api.api.PersonApi
import dagger.Binds
import dagger.Module

@Module
abstract class ApiModule {

    @Binds
    abstract fun bindChekApi(api: LeanTechServiceApi): PersonApi
}

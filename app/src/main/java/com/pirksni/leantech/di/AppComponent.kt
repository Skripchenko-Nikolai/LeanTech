package com.pirksni.leantech.di

import android.content.Context
import com.pirksni.leantech.LeanTechApplication
import com.pirksni.leantech.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        ActivityModule::class,
        FirebaseModule::class,
        FragmentModule::class,
        NavigationModule::class,
        ViewModelModule::class,
    ]
)
@Singleton
interface AppComponent : AndroidInjector<LeanTechApplication> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}

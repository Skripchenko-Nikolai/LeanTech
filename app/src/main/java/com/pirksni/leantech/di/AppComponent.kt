package com.pirksni.leantech.di

import android.content.Context
import com.pirksni.leantech.LeanTechApplication
import com.pirksni.leantech.di.module.ActivityModule
import com.pirksni.leantech.di.module.FirebaseModule
import com.pirksni.leantech.di.module.FragmentModule
import com.pirksni.leantech.di.module.ViewModelModule
import com.pirksni.leantech.di.module.PreferencesModule
import com.pirksni.leantech.di.module.RepositoryNetworkModule
import com.pirksni.leantech.di.module.RepositoryPreferencesModule
import com.pirksni.leantech.di.module.InteractorModule
import com.pirksni.leantech.di.module.MoshiModule
import com.pirksni.leantech.di.module.CoroutinesModule
import com.pirksni.leantech.di.module.GoogleSheetModule
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
        ViewModelModule::class,
        PreferencesModule::class,
        RepositoryPreferencesModule::class,
        RepositoryNetworkModule::class,
        InteractorModule::class,
        MoshiModule::class,
        CoroutinesModule::class,
        GoogleSheetModule::class,
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

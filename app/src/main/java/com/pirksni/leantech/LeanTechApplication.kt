package com.pirksni.leantech

import com.pirksni.leantech.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class LeanTechApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent
            .builder()
            .context(this)
            .build()
}

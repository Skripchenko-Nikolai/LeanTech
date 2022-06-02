package com.pirksni.leantech.presentation.screen.main

import android.os.Bundle
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.pirksni.leantech.R
import com.pirksni.leantech.extensions.unsafeLazy
import com.pirksni.leantech.presentation.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator by unsafeLazy { AppNavigator(this, R.id.container) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            navigatorHolder.setNavigator(navigator)
        }
    }
}

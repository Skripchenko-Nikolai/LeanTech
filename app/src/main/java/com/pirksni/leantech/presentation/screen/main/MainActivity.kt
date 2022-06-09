package com.pirksni.leantech.presentation.screen.main

import android.os.Bundle
import android.util.Log
import com.pirksni.leantech.R
import com.pirksni.leantech.presentation.base.BaseActivity

class MainActivity : BaseActivity<MainViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

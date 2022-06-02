package com.pirksni.leantech.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pirksni.leantech.di.ViewModelFactory
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel>(@LayoutRes contentLayoutId: Int) :
    Fragment(contentLayoutId), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var defaultViewModelFactory: ViewModelFactory

    protected val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[getViewModelClass()]
    }

    protected open val viewModelFactory: ViewModelProvider.Factory
        get() = defaultViewModelFactory

    override fun androidInjector() = androidInjector

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    @Suppress("UNCHECKED_CAST")
    private fun getViewModelClass() =
        (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
}

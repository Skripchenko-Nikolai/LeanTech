package com.pirksni.leantech.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

fun <T> Flow<T>.launchWhenStarted(lifecycleScope: LifecycleCoroutineScope) {
    lifecycleScope.launchWhenStarted {
        this@launchWhenStarted.collect()
    }
}

fun <T> Flow<T>.test(lifecycleScope: LifecycleCoroutineScope, lifecycle: Lifecycle) {
    lifecycleScope.launchWhenStarted {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            this@test.collect()
        }
    }
}

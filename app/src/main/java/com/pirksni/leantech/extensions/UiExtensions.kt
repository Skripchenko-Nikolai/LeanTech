package com.pirksni.leantech.extensions

fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE) { initializer() }

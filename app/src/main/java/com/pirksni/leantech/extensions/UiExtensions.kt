package com.pirksni.leantech.extensions

import android.view.View

fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE) { initializer() }

private var lastClickTimestamp = 0L

/**
 * setThrottledClickListener - прослушиватель кликов
 * не обрабатывет клики если время прошедшее с первого клика не больше чем установленное
 * @param delay это время которое должно пройти с момента первого клика чтобы началась обработка следующего
 * @param clickListener  выполнение переданного кода
 */
fun View.setThrottledClickListener(delay: Long = 500L, clickListener: (View) -> Unit) {
    setOnClickListener {
        val currentTime = System.currentTimeMillis()
        val delta = currentTime - lastClickTimestamp
        if (delta !in 0..delay) {
            lastClickTimestamp = currentTime
            clickListener(this)
        }
    }
}

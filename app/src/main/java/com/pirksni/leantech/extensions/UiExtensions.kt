package com.pirksni.leantech.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputLayout

private var lastClickTimestamp = 0L

fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE) { initializer() }

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

fun TextInputLayout.textChangeListener(onTextChange: (String) -> Unit) {
    this.editText?.doOnTextChanged { text, _, _, _ ->
        onTextChange(text.toString())
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View {
    val inflater = LayoutInflater.from(this.context)
    return inflater.inflate(layoutRes, this, false)
}

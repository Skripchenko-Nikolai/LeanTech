package com.pirksni.leantech.extensions

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

/**
 * showSnackbars - extension  для показа Snackbar
 * @param text - текст/строковой ресурс который будет показываться
 * @param duration - длительность показа Snackbar(LENGTH_LONG, LENGTH_SHORT, LENGTH_INDEFINITE)
 * начальное значение LENGTH_SHORT
 */
fun Fragment.showSnackbars(text: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(requireView(), text, duration).show()
}

fun Fragment.showSnackbars(@StringRes resource: Int, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(requireView(), resource, duration).show()
}

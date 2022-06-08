package com.pirksni.leantech.extensions

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding

fun View.updateBottomPaddingEdgeToEdge() {
    ViewCompat
        .setOnApplyWindowInsetsListener(this) { view, windowInsetsCompat ->
            val insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(
                bottom = insets.bottom
            )
            windowInsetsCompat
        }
}

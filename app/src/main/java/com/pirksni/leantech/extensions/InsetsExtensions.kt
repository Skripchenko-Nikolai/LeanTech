package com.pirksni.leantech.extensions

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding

/**
 * extension нужен для full screen ставит отсупы снизу относительно insets
 */
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

/**
 * extension нужен для full screen ставит отсупы снизу относительно insets
 */
fun View.updateTopPaddingEdgeToEdge() {
    ViewCompat
        .setOnApplyWindowInsetsListener(this) { view, windowInsetsCompat ->
            val insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(
                top = insets.top
            )
            windowInsetsCompat
        }
}

/**
 * extension нужен для full screen ставит отсупы сверху и снизу относительно insets и margin
 */
fun View.updateVerticalPaddingEdgeToEdge() {
    ViewCompat
        .setOnApplyWindowInsetsListener(this) { view, windowInsetsCompat ->
            val insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(
                top = insets.top,
                bottom = insets.bottom,
            )
            windowInsetsCompat
        }
}

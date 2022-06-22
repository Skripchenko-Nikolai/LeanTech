package com.pirksni.leantech.presentation.util

import androidx.annotation.StringRes
import com.google.android.material.datepicker.MaterialDatePicker

fun datePicker(@StringRes title: Int) =
    MaterialDatePicker.Builder.datePicker()
        .setTitleText(title)
        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
        .build()

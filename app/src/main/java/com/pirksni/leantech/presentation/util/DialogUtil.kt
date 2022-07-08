package com.pirksni.leantech.presentation.util

import android.content.Context
import android.content.DialogInterface
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pirksni.leantech.R

fun alertDialogBuilder(
    context: Context,
    title: Int,
    message: Int,
    positiveButtonCallback: () -> Unit = {},
    negativeButtonCallback: () -> Unit = {},
    negativeButtonName: Int,
    positiveButtonName: Int
) = MaterialAlertDialogBuilder(context, R.style.Theme_LeanTech_Dialog_Alert)
    .setTitle(title)
    .setMessage(message)
    .setCancelable(false)
    .setNegativeButton(negativeButtonName) { dialog: DialogInterface, _: Int ->
        negativeButtonCallback()
        dialog.dismiss()
    }
    .setPositiveButton(positiveButtonName) { dialog: DialogInterface, _: Int ->
        positiveButtonCallback()
        dialog.dismiss()
    }

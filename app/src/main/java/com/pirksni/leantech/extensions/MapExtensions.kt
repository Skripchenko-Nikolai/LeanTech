package com.pirksni.leantech.extensions

fun <T, R> Map<T, R>.mapToLists(): List<List<R>> =
    listOf(this.values.toList())

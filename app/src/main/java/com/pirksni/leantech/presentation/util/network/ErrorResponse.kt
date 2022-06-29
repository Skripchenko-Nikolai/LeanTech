package com.pirksni.leantech.presentation.util.network

import com.squareup.moshi.Json

data class ErrorResponse(
    @field:Json(name = "code") val code: String,
    @field:Json(name = "msg") val msg: String
)

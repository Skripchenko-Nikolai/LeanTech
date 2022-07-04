package com.pirksni.leantech.data.google.response

import com.squareup.moshi.Json

data class ValueResponse(
    @field:Json(name = "range") val range: String,
    @field:Json(name = "majorDimension") val majorDimension: String,
    @field:Json(name = "values") val value: List<List<String>>,
)

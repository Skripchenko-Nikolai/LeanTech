package com.pirksni.leantech.data.api.response

import com.squareup.moshi.Json

data class PersonResponse(
    @field:Json(name = "range") val range: String,
    @field:Json(name = "majorDimension") val majorDimension: String,
    @field:Json(name = "values") val persons: List<List<String>>,
)

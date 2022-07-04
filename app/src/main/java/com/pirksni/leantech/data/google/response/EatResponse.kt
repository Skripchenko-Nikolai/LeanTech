package com.pirksni.leantech.data.google.response

import com.squareup.moshi.Json

data class EatResponse(
    @field:Json(name = "spreadsheetId") val spreadsheetId: String,
    @field:Json(name = "updatedRange") val updatedRange: String,
    @field:Json(name = "updatedRows") val updatedRows: String,
    @field:Json(name = "updatedColumns") val updatedColumns: String,
    @field:Json(name = "updatedCells") val updatedCells: String,
)

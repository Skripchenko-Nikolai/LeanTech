package com.pirksni.leantech.data.api.response

import com.google.gson.annotations.SerializedName

data class PersonResponse(
    @SerializedName("range") val range: String,
    @SerializedName("majorDimension") val majorDimension: String,
    @SerializedName("values") val persons: List<List<String>>,
)

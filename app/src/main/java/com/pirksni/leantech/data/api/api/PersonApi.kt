package com.pirksni.leantech.data.api.api

import com.pirksni.leantech.data.api.response.PersonResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonApi {

    @GET("$SHEET_ID/values/$RANGE_PERSON")
    suspend fun getPerson(
        @Query("key") key: String = KEY
    ): PersonResponse


    companion object {
        private const val SHEET_ID = "1VZTN0nFodYskOvJeE9-SNBefZKnX1CICPyWtQwWCuNo"
        private const val RANGE_PERSON = "A2:B45"
        private const val KEY = "AIzaSyCzRVyrJnUeNMWcAps6vpb1S4bSYnkFW2w"
    }
}

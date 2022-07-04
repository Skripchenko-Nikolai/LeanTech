package com.pirksni.leantech.data.api.api

import com.pirksni.leantech.data.api.ApiConstants.KEY
import com.pirksni.leantech.data.api.ApiConstants.RANGE_PERSON
import com.pirksni.leantech.data.api.ApiConstants.RANGE_PERSON_EAT
import com.pirksni.leantech.data.api.ApiConstants.SHEET_ID
import com.pirksni.leantech.data.api.response.PersonEatResponse
import com.pirksni.leantech.data.api.response.PersonResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonApi {

    @GET("$SHEET_ID/values/$RANGE_PERSON")
    suspend fun getPerson(
        @Query("key") key: String = KEY
    ): PersonResponse

    @GET("$SHEET_ID/values/$RANGE_PERSON_EAT")
    suspend fun getPersonEat(
        @Query("key") key: String = KEY
    ): PersonEatResponse
}

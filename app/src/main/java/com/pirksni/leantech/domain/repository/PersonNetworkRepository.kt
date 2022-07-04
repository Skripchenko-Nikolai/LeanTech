package com.pirksni.leantech.domain.repository

import com.pirksni.leantech.data.google.response.EatResponse
import com.pirksni.leantech.domain.model.PersonModel

interface PersonNetworkRepository {

    suspend fun getPersons(): List<PersonModel>?
    suspend fun getEats(): List<String>?
    suspend fun updateEat(
        range: String,
        values: List<List<Any?>?>?,
    ): EatResponse?
}

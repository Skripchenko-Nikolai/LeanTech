package com.pirksni.leantech.presentation.interactor

import com.pirksni.leantech.data.google.response.EatResponse
import com.pirksni.leantech.domain.model.PersonModel

interface PersonInteractor {

    suspend fun getPerson(): List<PersonModel>?
    suspend fun getEats(): List<String>?
    suspend fun updateEat(
        range: String,
        values: List<List<Any?>?>?,
    ): EatResponse?
}

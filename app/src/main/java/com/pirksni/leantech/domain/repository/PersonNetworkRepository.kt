package com.pirksni.leantech.domain.repository

import com.pirksni.leantech.domain.model.PersonModel

interface PersonNetworkRepository {

    suspend fun putPersons(): List<PersonModel>
}

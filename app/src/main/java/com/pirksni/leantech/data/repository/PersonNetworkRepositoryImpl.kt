package com.pirksni.leantech.data.repository

import com.pirksni.leantech.data.api.api.PersonApi
import com.pirksni.leantech.domain.model.PersonModel
import com.pirksni.leantech.domain.repository.PersonNetworkRepository
import com.pirksni.leantech.extensions.mapToPersonModel
import javax.inject.Inject

class PersonNetworkRepositoryImpl @Inject constructor(
    private val personApi: PersonApi,
) : PersonNetworkRepository {

    override suspend fun putPersons(): List<PersonModel> =
        personApi.putPerson().mapToPersonModel()
}

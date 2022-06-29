package com.pirksni.leantech.data.repository

import com.pirksni.leantech.data.api.api.PersonApi
import com.pirksni.leantech.domain.model.PersonModel
import com.pirksni.leantech.domain.repository.PersonNetworkRepository
import com.pirksni.leantech.extensions.mapToPersonModel
import com.pirksni.leantech.presentation.util.network.ResultWrapper
import com.pirksni.leantech.presentation.util.network.safeApiCall
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class PersonNetworkRepositoryImpl @Inject constructor(
    private val personApi: PersonApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val moshi: Moshi,
) : PersonNetworkRepository {

    override suspend fun getPersons(): ResultWrapper<List<PersonModel>> =
        safeApiCall(dispatcher, moshi) {
            personApi.getPerson().mapToPersonModel()
        }
}

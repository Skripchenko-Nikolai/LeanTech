package com.pirksni.leantech.domain.interactor

import com.pirksni.leantech.data.google.response.EatResponse
import com.pirksni.leantech.domain.model.PersonModel
import com.pirksni.leantech.domain.repository.PersonNetworkRepository
import com.pirksni.leantech.presentation.interactor.PersonInteractor
import javax.inject.Inject

class PersonInteractorImpl @Inject constructor(
    private val personNetworkRepository: PersonNetworkRepository
) : PersonInteractor {

    override suspend fun getPerson(): List<PersonModel>? =
        personNetworkRepository.getPersons()

    override suspend fun getEats(): List<String>? =
        personNetworkRepository.getEats()

    override suspend fun updateEat(
        range: String,
        values: List<List<Any?>?>?
    ): EatResponse? =
        personNetworkRepository.updateEat(range, values)
}

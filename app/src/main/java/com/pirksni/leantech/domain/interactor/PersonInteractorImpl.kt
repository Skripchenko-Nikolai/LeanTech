package com.pirksni.leantech.domain.interactor

import com.pirksni.leantech.domain.model.PersonModel
import com.pirksni.leantech.domain.repository.PersonNetworkRepository
import com.pirksni.leantech.presentation.interactor.PersonInteractor
import javax.inject.Inject

class PersonInteractorImpl @Inject constructor(
    private val personNetworkRepository: PersonNetworkRepository
) : PersonInteractor {

    override suspend fun getPerson(): List<PersonModel> =
        personNetworkRepository.getPersons()
}

package com.pirksni.leantech.domain.interactor

import com.pirksni.leantech.domain.model.PersonEatModel
import com.pirksni.leantech.domain.model.PersonModel
import com.pirksni.leantech.domain.repository.PersonNetworkRepository
import com.pirksni.leantech.presentation.interactor.PersonInteractor
import com.pirksni.leantech.presentation.util.network.ResultWrapper
import javax.inject.Inject

class PersonInteractorImpl @Inject constructor(
    private val personNetworkRepository: PersonNetworkRepository
) : PersonInteractor {

    override suspend fun getPerson(): ResultWrapper<List<PersonModel>> =
        personNetworkRepository.getPersons()

    override suspend fun getPersonEat(): ResultWrapper<List<PersonEatModel>> =
        personNetworkRepository.getPersonEat()
}

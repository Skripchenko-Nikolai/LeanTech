package com.pirksni.leantech.domain.repository

import com.pirksni.leantech.domain.model.PersonEatModel
import com.pirksni.leantech.domain.model.PersonModel
import com.pirksni.leantech.presentation.util.network.ResultWrapper

interface PersonNetworkRepository {

    suspend fun getPersons(): ResultWrapper<List<PersonModel>>
    suspend fun getPersonEat(): ResultWrapper<List<PersonEatModel>>
}

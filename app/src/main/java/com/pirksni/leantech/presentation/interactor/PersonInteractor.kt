package com.pirksni.leantech.presentation.interactor

import com.pirksni.leantech.domain.model.PersonModel
import com.pirksni.leantech.presentation.util.network.ResultWrapper

interface PersonInteractor {

    suspend fun getPerson(): ResultWrapper<List<PersonModel>>
    suspend fun getPersonEat(): ResultWrapper<List<String>>
}

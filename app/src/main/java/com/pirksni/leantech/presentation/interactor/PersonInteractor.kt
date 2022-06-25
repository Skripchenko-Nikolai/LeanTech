package com.pirksni.leantech.presentation.interactor

import com.pirksni.leantech.domain.model.PersonModel

interface PersonInteractor {

    suspend fun getPerson(): List<PersonModel>
}

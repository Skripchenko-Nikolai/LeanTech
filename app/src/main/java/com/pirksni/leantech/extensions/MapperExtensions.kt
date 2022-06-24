package com.pirksni.leantech.extensions

import com.pirksni.leantech.data.api.response.PersonResponse
import com.pirksni.leantech.domain.model.PersonModel
import com.pirksni.leantech.domain.model.ProfileModel
import com.pirksni.leantech.presentation.screen.filledprofile.FilledProfileState

fun FilledProfileState.Model.mapToProfileModel(): ProfileModel =
    ProfileModel(
        name = this.name,
        secondName = this.secondName,
        patronymic = this.patronymic,
        dayBirthday = this.dayBirthday,
        position = this.position,
        numberPhone = this.numberPhone,
        nicknameTelegram = this.nicknameTelegram
    )

fun PersonResponse.mapToPersonModel(): List<PersonModel> =
    this.persons.map { personValueResponse ->
        PersonModel(
            number = personValueResponse.value[0],
            fullName = personValueResponse.value[1]
        )
    }

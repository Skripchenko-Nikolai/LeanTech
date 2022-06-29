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
        birthday = this.dayBirthday,
        position = this.position,
        phoneNumber = this.numberPhone,
        telegramNickname = this.nicknameTelegram
    )

fun PersonResponse.mapToPersonModel(): List<PersonModel> =
    this.persons.filter { it.size > 1 }.map {
        PersonModel(
            number = it[0],
            fullName = it[1]
        )
    }

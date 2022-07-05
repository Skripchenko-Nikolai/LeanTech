package com.pirksni.leantech.extensions

import com.pirksni.leantech.data.google.response.ValueResponse
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

fun ValueResponse.mapToPersonModel(): List<PersonModel> =
    this.value.filter { it.size > 1 }.map {
        PersonModel(
            number = it[0],
            fullName = it[1]
        )
    }

fun ValueResponse.mapToPersonEatModel(): List<String> =
    this.value.flatten()

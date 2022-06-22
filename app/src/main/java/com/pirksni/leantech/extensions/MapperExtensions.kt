package com.pirksni.leantech.extensions

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

package com.pirksni.leantech.extensions

import com.pirksni.leantech.domain.model.ProfileModel
import com.pirksni.leantech.presentation.screen.filledprofile.FilledProfileState

fun FilledProfileState.Model.mapToProfileModel(): ProfileModel =
    ProfileModel(
        name = this.name,
        secondName = this.secondName,
        birthday = this.dayBirthday,
        position = this.position,
        phoneNumber = this.numberPhone,
        telegramNickname = this.nicknameTelegram
    )

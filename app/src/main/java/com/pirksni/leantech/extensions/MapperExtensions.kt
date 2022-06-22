package com.pirksni.leantech.extensions

import com.pirksni.leantech.domain.model.ProfileModel
import com.pirksni.leantech.presentation.screen.filledprofile.FilledProfileState

fun FilledProfileState.Model.mapToProfileModel(): ProfileModel =
    ProfileModel(
        email = this.email,
        name = this.name,
        secondName = this.secondName,
        position = this.position,
    )

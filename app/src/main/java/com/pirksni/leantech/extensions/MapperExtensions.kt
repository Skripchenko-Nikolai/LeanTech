package com.pirksni.leantech.extensions

import com.pirksni.leantech.data.api.response.PersonEatResponse
import com.pirksni.leantech.data.api.response.PersonResponse
import com.pirksni.leantech.domain.model.PersonModel
import com.pirksni.leantech.domain.model.PointModel
import com.pirksni.leantech.domain.model.ProfileModel
import com.pirksni.leantech.presentation.screen.filledprofile.FilledProfileState
import com.pirksni.leantech.presentation.screen.officemap.OfficeMapState

private const val EMAIL_POINT_KEY = "email"
private const val NAME_POINT_KEY = "name"
private const val SECOND_NAME_POINT_KEY = "secondName"
private const val COORDINATE_X_POINT_KEY = "coordinateX"
private const val COORDINATE_Y_POINT_KEY = "coordinateY"

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

fun PersonEatResponse.mapToPersonEatModel(): List<String> =
    this.personEat.flatten()

fun PointModel.mapToHashMapPoint(): HashMap<String, Any> =
    hashMapOf(
        EMAIL_POINT_KEY to this.email,
        NAME_POINT_KEY to this.name,
        SECOND_NAME_POINT_KEY to this.secondName,
        COORDINATE_X_POINT_KEY to this.coordinateX,
        COORDINATE_Y_POINT_KEY to this.coordinateY
    )

fun OfficeMapState.Model.mapToPointModel(): PointModel =
    PointModel(
        email = this.email,
        name = this.name,
        secondName = this.secondName,
        coordinateX = this.coordinateX,
        coordinateY = this.coordinateY
    )

fun List<Any>.mapToPointModel() =
    PointModel(
        coordinateX = this[0] as Double,
        coordinateY = this[1] as Double,
        email = this[3] as String,
        name = this[2] as String,
        secondName = this[4] as String
    )

package com.pirksni.leantech.domain.model

data class ProfileModel(
    val name: String,
    val secondName: String,
    val birthday: Long,
    val position: String,
    val phoneNumber: String,
    val telegramNickname: String,
)

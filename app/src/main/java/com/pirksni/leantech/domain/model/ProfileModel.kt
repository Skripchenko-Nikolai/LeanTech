package com.pirksni.leantech.domain.model

data class ProfileModel(
    val name: String,
    val secondName: String,
    val patronymic: String,
    val dayBirthday: Long,
    val position: String,
    val numberPhone: String,
    val nicknameTelegram: String,
)

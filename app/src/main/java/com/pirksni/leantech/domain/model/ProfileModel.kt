package com.pirksni.leantech.domain.model

/*
    Поля могут быть null потому что сначала мы сохраняем email на экране авторизации, а потом заполняем другие поля
 */
data class ProfileModel(
    val email: String,
    val name: String?,
    val secondName: String?,
    val position: String?,
)

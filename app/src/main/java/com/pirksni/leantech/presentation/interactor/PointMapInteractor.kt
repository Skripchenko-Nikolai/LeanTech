package com.pirksni.leantech.presentation.interactor

import com.pirksni.leantech.data.firebase.FirebaseState
import com.pirksni.leantech.domain.model.PointModel
import com.pirksni.leantech.presentation.screen.officemap.OfficeMapState

interface PointMapInteractor {

    suspend fun setPointValue(model: OfficeMapState.Model): FirebaseState
    suspend fun getPoint(): List<PointModel>
}

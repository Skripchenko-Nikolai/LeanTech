package com.pirksni.leantech.domain.repository

import com.pirksni.leantech.data.firebase.FirebaseState
import com.pirksni.leantech.domain.model.PointModel

interface PointMapNetworkRepository {

    suspend fun setPointValue(pointModel: PointModel): FirebaseState
    suspend fun getPoint(): List<PointModel>
}

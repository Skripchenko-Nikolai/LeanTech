package com.pirksni.leantech.data.repository

import com.pirksni.leantech.data.firebase.FirebasePointMap
import com.pirksni.leantech.data.firebase.FirebaseState
import com.pirksni.leantech.domain.model.PointModel
import com.pirksni.leantech.domain.repository.PointMapNetworkRepository
import com.pirksni.leantech.extensions.mapToHashMapPoint
import javax.inject.Inject

class PointMapNetworkRepositoryImpl @Inject constructor(
    private val firebasePointMap: FirebasePointMap
) : PointMapNetworkRepository {

    override suspend fun setPointValue(pointModel: PointModel): FirebaseState =
        firebasePointMap.setPoint(pointModel.mapToHashMapPoint())

    override suspend fun getPoint(): List<PointModel> =
        firebasePointMap.getPoints()
}

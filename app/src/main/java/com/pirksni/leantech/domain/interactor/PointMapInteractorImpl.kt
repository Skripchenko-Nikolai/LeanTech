package com.pirksni.leantech.domain.interactor

import com.pirksni.leantech.data.firebase.FirebaseState
import com.pirksni.leantech.domain.model.PointModel
import com.pirksni.leantech.domain.repository.PointMapNetworkRepository
import com.pirksni.leantech.extensions.mapToPointModel
import com.pirksni.leantech.presentation.interactor.PointMapInteractor
import com.pirksni.leantech.presentation.screen.officemap.OfficeMapState
import javax.inject.Inject

class PointMapInteractorImpl @Inject constructor(
    private val pointMapNetworkRepository: PointMapNetworkRepository
) : PointMapInteractor {

    override suspend fun setPointValue(model: OfficeMapState.Model): FirebaseState =
        pointMapNetworkRepository.setPointValue(model.mapToPointModel())

    override suspend fun getPoint(): List<PointModel> =
        pointMapNetworkRepository.getPoint()
}

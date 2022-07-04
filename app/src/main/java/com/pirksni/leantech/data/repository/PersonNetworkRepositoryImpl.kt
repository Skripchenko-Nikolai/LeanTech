package com.pirksni.leantech.data.repository

import com.pirksni.leantech.data.google.GetValueSheet
import com.pirksni.leantech.data.google.UpdateValuesSheet
import com.pirksni.leantech.data.google.response.EatResponse
import com.pirksni.leantech.domain.model.PersonModel
import com.pirksni.leantech.domain.repository.PersonNetworkRepository
import com.pirksni.leantech.extensions.mapToPersonEatModel
import com.pirksni.leantech.extensions.mapToPersonModel
import javax.inject.Inject

class PersonNetworkRepositoryImpl @Inject constructor(
    private val getValueSheet: GetValueSheet,
    private val updateValuesSheet: UpdateValuesSheet,
) : PersonNetworkRepository {

    override suspend fun getPersons(): List<PersonModel>? =
        getValueSheet.getValues(SHEET_ID, RANGE_PERSON)?.mapToPersonModel()

    override suspend fun getEats(): List<String>? =
        getValueSheet.getValues(SHEET_ID, RANGE_EAT)?.mapToPersonEatModel()

    override suspend fun updateEat(
        range: String,
        values: List<List<Any?>?>?,
    ): EatResponse? =
        updateValuesSheet.updateValues(
            spreadsheetId = SHEET_ID,
            range = range,
            values = values
        )

    companion object {
        private const val SHEET_ID = "1VZTN0nFodYskOvJeE9-SNBefZKnX1CICPyWtQwWCuNo"
        private const val RANGE_PERSON = "A2:B100"
        private const val RANGE_EAT = "C1:Z1"
    }
}

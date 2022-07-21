package com.pirksni.leantech.data.firebase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.pirksni.leantech.domain.model.PointModel
import com.pirksni.leantech.extensions.mapToPointModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirebasePointMap @Inject constructor(
    private val fireStoreFirebase: FirebaseFirestore
) {

    fun setPoint(pointValue: HashMap<String, Any>): FirebaseState {
        var value: FirebaseState = FirebaseState.DEFAULT
        fireStoreFirebase.collection(COLLECTION_POINT_ON_MAP_PERSON)
            .add(pointValue)
            .addOnSuccessListener {
                value = FirebaseState.SUCCESS
            }
            .addOnFailureListener {
                value = FirebaseState.FAILURE
            }
        return value
    }

    private val values = mutableListOf<PointModel>()
    private var status = true

    fun getPointsNew() : Flow<List<PointModel>> {
        return callbackFlow<List<PointModel>> {
            fireStoreFirebase.collection(COLLECTION_POINT_ON_MAP_PERSON)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    querySnapshot.onEach { document ->
                        values.add(document.data.values.toList().mapToPointModel())
                    }
                    trySend(values)
                }
                .addOnFailureListener {
                    error("Error")
                }
        }
    }

    fun getPoints(): List<PointModel> {
        addPoints()
        // жду пока отработает getPoints  и запишет все значения
        while (status) {
            // ignore
        }
        return values
    }

    private fun addPoints() {
        fireStoreFirebase.collection(COLLECTION_POINT_ON_MAP_PERSON)
            .get()
            .addOnSuccessListener { querySnapshot ->
                querySnapshot.onEach { document ->
                    values.add(document.data.values.toList().mapToPointModel())
                }
                status = false
            }
            .addOnFailureListener {
                status = false
            }
    }

    companion object {
        private const val COLLECTION_POINT_ON_MAP_PERSON = "point_person_map"
        private const val ERROR_WRAPPER_VALUE = "Не удалось получить значения"
    }
}

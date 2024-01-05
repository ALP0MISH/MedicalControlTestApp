package com.example.realmlessons.data.cashe.source

import com.example.realmlessons.data.cashe.models.CameraCash
import kotlinx.coroutines.flow.Flow

interface CommonDataSource {

    fun observeAllSavedCamera(): Flow<List<CameraCash>>

    suspend fun saveCamera(saveDoor: CameraCash)

    suspend fun deleteCameraById(id: Int)

    suspend fun fetchSavedCameraById(id: Int): Boolean
}
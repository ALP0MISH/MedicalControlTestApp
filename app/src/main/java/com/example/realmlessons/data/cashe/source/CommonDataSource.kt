package com.example.realmlessons.data.cashe.source

import com.example.realmlessons.data.cashe.models.CameraCash
import kotlinx.coroutines.flow.Flow

interface CommonDataSource {

    fun observeAllSavedCamera(): Flow<List<Int>>

    suspend fun saveCamera(saveDoor: CameraCash)

    suspend fun deleteCameraById(id: Int)

    fun fetchSavedCameraById(id: Int): Flow<Boolean>
}
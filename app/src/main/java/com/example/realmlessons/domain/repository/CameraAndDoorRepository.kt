package com.example.realmlessons.domain.repository

import com.example.realmlessons.data.common.model.ResultModel
import com.example.realmlessons.domain.models.CameraDomain
import com.example.realmlessons.domain.models.DoorDomain
import kotlinx.coroutines.flow.Flow

interface CameraAndDoorRepository {

    fun observeAllSavedCamera(): Flow<List<Int>>

    suspend fun saveCamera(saveCamera: CameraDomain)

    suspend fun deleteCameraById(id: Int)

    fun fetchSavedCameraById(id: Int): Flow<Boolean>

    suspend fun fetchCloudDoor(): ResultModel<List<DoorDomain>>

    suspend fun fetchCloudCamera(): ResultModel<List<CameraDomain>>
}
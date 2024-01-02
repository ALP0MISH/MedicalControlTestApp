package com.example.realmlessons.domain.repository

import com.example.realmlessons.data.common.model.ResultModel
import com.example.realmlessons.domain.models.CameraDomain
import com.example.realmlessons.domain.models.DoorDomain

interface CameraAndDoorRepository {

    suspend fun saveCamera(saveCamera: CameraDomain)

    suspend fun deleteCameraById(id: Int)

    suspend fun fetchSavedCameraById(id: Int): kotlinx.coroutines.flow.Flow<Boolean>

    suspend fun fetchCloudDoor(): ResultModel<List<DoorDomain>>

    suspend fun fetchCloudCamera(): ResultModel<List<CameraDomain>>
}
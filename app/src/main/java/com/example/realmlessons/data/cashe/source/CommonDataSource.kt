package com.example.realmlessons.data.cashe.source

import com.example.realmlessons.data.cashe.models.CameraCash
import com.example.realmlessons.data.cloud.models.camera.CameraCloud
import com.example.realmlessons.domain.models.CameraDomain

interface CommonDataSource {

    suspend fun fetchCamera(saveDoor: CameraCash)

    suspend fun deleteCameraById(id: Int)

    suspend fun fetchSavedCameraById(id: Int): kotlinx.coroutines.flow.Flow<Boolean>
}
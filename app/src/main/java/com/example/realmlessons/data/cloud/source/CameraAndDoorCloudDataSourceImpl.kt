package com.example.realmlessons.data.cloud.source

import com.example.realmlessons.data.common.BaseDataSource
import com.example.realmlessons.data.common.model.ResultModel
import com.example.realmlessons.data.cloud.models.camera.CameraResponseResult
import com.example.realmlessons.data.cloud.models.door.DoorResponseResult
import com.example.realmlessons.data.cloud.service.CameraAndDoorService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CameraAndDoorCloudDataSourceImpl @Inject constructor(
    private val service: CameraAndDoorService
) : BaseDataSource(), CameraAndDoorCloudDataSource {

    override suspend fun fetchCloudDoor(
    ): ResultModel<DoorResponseResult> = withContext(Dispatchers.IO) {
        invokeRequest { service.fetchDoor() }
    }


    override suspend fun fetchCloudCamera(
    ): ResultModel<CameraResponseResult> = withContext(Dispatchers.IO) {
        invokeRequest {
            service.fetchCamera()
        }
    }
}
package com.example.realmlessons.data.cloud.source

import com.example.realmlessons.data.common.model.ResultModel
import com.example.realmlessons.data.cloud.models.camera.CameraResponseResult
import com.example.realmlessons.data.cloud.models.door.DoorResponseResult

interface CameraAndDoorCloudDataSource {

    suspend fun fetchCloudDoor(): ResultModel<DoorResponseResult>

    suspend fun fetchCloudCamera(): ResultModel<CameraResponseResult>

}
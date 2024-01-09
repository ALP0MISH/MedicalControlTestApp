package com.example.realmlessons.domain.usecase

import com.example.realmlessons.data.common.model.ResultModel
import com.example.realmlessons.domain.models.CameraDomain
import com.example.realmlessons.domain.models.DoorDomain

interface FetchCameraAndDoorUseCase {

    suspend fun fetchCloudDoor(): ResultModel<List<DoorDomain>>

    suspend fun fetchCloudCamera(): ResultModel<List<CameraDomain>>
}
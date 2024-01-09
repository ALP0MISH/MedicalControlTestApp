package com.example.realmlessons.domain.usecase

import com.example.realmlessons.data.common.model.ResultModel
import com.example.realmlessons.domain.models.CameraDomain
import com.example.realmlessons.domain.models.DoorDomain
import com.example.realmlessons.domain.repository.CameraAndDoorRepository
import javax.inject.Inject

class FetchCameraAndDoorUseCaseImpl @Inject constructor(
    private val repository: CameraAndDoorRepository
) : FetchCameraAndDoorUseCase {

    override suspend fun fetchCloudDoor(): ResultModel<List<DoorDomain>> {
        return repository.fetchCloudDoor()
    }

    override suspend fun fetchCloudCamera(): ResultModel<List<CameraDomain>> {
        return repository.fetchCloudCamera()
    }
}
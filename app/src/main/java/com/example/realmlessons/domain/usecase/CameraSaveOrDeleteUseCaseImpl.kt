package com.example.realmlessons.domain.usecase

import com.example.realmlessons.domain.models.CameraDomain
import com.example.realmlessons.domain.repository.CameraAndDoorRepository
import javax.inject.Inject

class CameraSaveOrDeleteUseCaseImpl @Inject constructor(
    private val repository: CameraAndDoorRepository
) : CameraSaveOrDeleteUseCase {

    override suspend fun saveCamera(camera: CameraDomain) {
        return repository.saveCamera(camera)
    }

    override suspend fun deleteCameraById(id: Int) {
        return repository.deleteCameraById(id)
    }

}
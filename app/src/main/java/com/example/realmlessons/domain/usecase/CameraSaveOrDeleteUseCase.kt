package com.example.realmlessons.domain.usecase

import com.example.realmlessons.domain.models.CameraDomain

interface CameraSaveOrDeleteUseCase {

    suspend fun saveCamera(camera: CameraDomain)

    suspend fun deleteCameraById(id: Int)
}
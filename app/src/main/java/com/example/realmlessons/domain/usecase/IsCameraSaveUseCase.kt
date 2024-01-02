package com.example.realmlessons.domain.usecase

import com.example.realmlessons.domain.models.CameraDomain

interface IsCameraSaveUseCase {

    suspend fun saveCamera(camera: CameraDomain)

    suspend fun deleteCameraById(id: Int)
}
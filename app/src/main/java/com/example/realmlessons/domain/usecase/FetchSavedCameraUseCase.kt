package com.example.realmlessons.domain.usecase

import com.example.realmlessons.domain.repository.CameraAndDoorRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FetchSavedCameraUseCase {

    suspend operator fun invoke(id: Int): Boolean
}

class FetchSavedCameraUseCaseImpl @Inject constructor(
    private val repository: CameraAndDoorRepository
) : FetchSavedCameraUseCase {
    override suspend fun invoke(id: Int): Boolean {
        return repository.fetchSavedCameraById(id)
    }
}
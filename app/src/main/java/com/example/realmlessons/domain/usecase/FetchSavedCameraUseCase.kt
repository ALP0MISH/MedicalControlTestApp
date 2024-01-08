package com.example.realmlessons.domain.usecase

import com.example.realmlessons.domain.repository.CameraAndDoorRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FetchSavedCameraUseCase {

    operator fun invoke(id: Int): Flow<Boolean>
}

class FetchSavedCameraUseCaseImpl @Inject constructor(
    private val repository: CameraAndDoorRepository
) : FetchSavedCameraUseCase {
    override fun invoke(id: Int): Flow<Boolean> {
        return repository.fetchSavedCameraById(id)
    }
}
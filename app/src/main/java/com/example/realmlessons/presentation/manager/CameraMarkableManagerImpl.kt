package com.example.realmlessons.presentation.manager

import android.util.Log
import com.example.realmlessons.domain.models.CameraDomain
import com.example.realmlessons.domain.repository.CameraAndDoorRepository
import com.example.realmlessons.presentation.models.Camera
import com.example.realmlessons.presentation.models.CameraMark
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CameraMarkableManagerImpl @Inject constructor(
    private val repository: CameraAndDoorRepository
) : CameraMarkableManager {
    override fun observeCameraMarks(cameras: List<CameraDomain>): Flow<List<CameraMark>> {
        return repository.observeAllSavedCamera().map { savedCameras ->
            
            cameras.map { cameraDomain ->
                CameraMark(
                    camera = Camera(
                        id = cameraDomain.id,
                        rec = cameraDomain.rec,
                        favorites = cameraDomain.favorites,
                        name = cameraDomain.name,
                        room = cameraDomain.room ?: String(),
                        snapshot = cameraDomain.snapshot,
                    ),
                    isSaved = savedCameras.contains(cameraDomain.id)
                )
            }
        }
    }
}
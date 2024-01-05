package com.example.realmlessons.presentation.manager

import com.example.realmlessons.domain.models.CameraDomain
import com.example.realmlessons.presentation.models.CameraMark
import kotlinx.coroutines.flow.Flow

interface CameraMarkableManager {

    fun observeCameraMarks(cameras: List<CameraDomain>): Flow<List<CameraMark>>
}
package com.example.realmlessons.data.cashe.source

import android.util.Log
import com.example.realmlessons.data.cashe.dao.CameraDao
import com.example.realmlessons.data.cashe.models.CameraCash
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class CameraDataSourceImpl @Inject constructor(
    private val commonDao: CameraDao
) : CameraDataSource {

    override fun observeAllSavedCamera(): Flow<List<Int>> {
        return commonDao.observeAllSavedCamera().onEach {
        }
    }

    override suspend fun saveCamera(saveDoor: CameraCash) {
        return commonDao.saveCamera(saveDoor)
    }

    override fun fetchSavedCameraById(id: Int): Flow<Boolean> {
        return commonDao.fetchSavedCameraById(id)
    }

    override suspend fun deleteCameraById(id: Int) {
        return commonDao.deleteCameraById(id)
    }
}
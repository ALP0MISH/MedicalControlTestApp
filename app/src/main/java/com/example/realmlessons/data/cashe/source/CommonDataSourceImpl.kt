package com.example.realmlessons.data.cashe.source

import com.example.realmlessons.data.cashe.dao.CommonDao
import com.example.realmlessons.data.cashe.models.CameraCash
import com.example.realmlessons.domain.models.CameraDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CommonDataSourceImpl @Inject constructor(
    private val commonDao: CommonDao
) : CommonDataSource {

    override suspend fun fetchCamera(saveDoor: CameraCash) {
        return commonDao.saveCamera(saveDoor)
    }

    override suspend fun fetchSavedCameraById(id: Int): Flow<Boolean> {
        return commonDao.fetchSavedCameraById(id)
    }

    override suspend fun deleteCameraById(id: Int) {
        return commonDao.deleteCameraById(id)
    }
}
package com.example.realmlessons.data.cashe.source

import android.util.Log
import com.example.realmlessons.data.cashe.dao.CommonDao
import com.example.realmlessons.data.cashe.models.CameraCash
import com.example.realmlessons.domain.models.CameraDomain
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class CommonDataSourceImpl @Inject constructor(
    private val commonDao: CommonDao
) : CommonDataSource {

    override fun observeAllSavedCamera(): Flow<List<CameraCash>> {
        return commonDao.observeAllSavedCamera().onEach {
            Log.i("PPP", "camers: $it")
        }
    }

    override suspend fun saveCamera(saveDoor: CameraCash) {
        Log.i("PPP", "saved: $saveDoor")
        return commonDao.saveCamera(saveDoor)
    }

    override suspend fun fetchSavedCameraById(id: Int): Boolean {
        return commonDao.fetchSavedCameraById(id)
    }

    override suspend fun deleteCameraById(id: Int) {
        Log.i("PPP", "deleted: $id")
        return commonDao.deleteCameraById(id)
    }
}
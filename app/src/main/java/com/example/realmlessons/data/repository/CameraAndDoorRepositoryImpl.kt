package com.example.realmlessons.data.repository

import com.example.realmlessons.data.common.model.ResultModel
import com.example.realmlessons.data.cashe.source.CameraDataSource
import com.example.realmlessons.data.cloud.source.CameraAndDoorCloudDataSource
import com.example.realmlessons.data.common.mapper.toDomain
import com.example.realmlessons.data.common.mapper.toCameraCash
import com.example.realmlessons.domain.models.CameraDomain
import com.example.realmlessons.domain.models.DoorDomain
import com.example.realmlessons.domain.repository.CameraAndDoorRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CameraAndDoorRepositoryImpl @Inject constructor(
    private val commonDataSource: CameraDataSource,
    private val cloudDataSource: CameraAndDoorCloudDataSource
) : CameraAndDoorRepository {

    override fun fetchSavedCameraById(id: Int): Flow<Boolean> {
        return commonDataSource.fetchSavedCameraById(id)
    }

    override suspend fun fetchCloudDoor(): ResultModel<List<DoorDomain>> {
        val response = cloudDataSource.fetchCloudDoor()
        return ResultModel(
            status = response.status,
            data = response.data?.data?.map { it.toDomain() },
            errorThrowable = response.errorThrowable,
        )
    }

    override suspend fun fetchCloudCamera(): ResultModel<List<CameraDomain>> {
        val response = cloudDataSource.fetchCloudCamera()
        return ResultModel(
            status = response.status,
            data = response.data?.data?.cameras?.map { it.toDomain() }
        )
    }

    override fun observeAllSavedCamera(): Flow<List<Int>> {
        return commonDataSource.observeAllSavedCamera()
    }

    override suspend fun saveCamera(saveCamera: CameraDomain) {
        commonDataSource.saveCamera(saveCamera.toCameraCash())
    }

    override suspend fun deleteCameraById(id: Int) {
        return commonDataSource.deleteCameraById(id)
    }
}
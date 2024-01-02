package com.example.realmlessons.data.repository

import com.example.realmlessons.data.common.model.ResultModel
import com.example.realmlessons.data.cashe.source.CommonDataSource
import com.example.realmlessons.data.cloud.source.CameraAndDoorCloudDataSource
import com.example.realmlessons.data.mapper.toDomain
import com.example.realmlessons.domain.models.CameraDomain
import com.example.realmlessons.domain.models.DoorDomain
import com.example.realmlessons.domain.repository.CameraAndDoorRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CameraAndDoorRepositoryImpl @Inject constructor(
    private val commonDataSource: CommonDataSource,
    private val cloudDataSource: CameraAndDoorCloudDataSource
) : CameraAndDoorRepository {

    override suspend fun fetchSavedCameraById(id: Int): Flow<Boolean> {
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


    override suspend fun saveCamera(saveCamera: CameraDomain) {
        commonDataSource.fetchCamera(saveCamera.toDomain())
    }

    override suspend fun deleteCameraById(id: Int) {
        return commonDataSource.deleteCameraById(id)
    }
}
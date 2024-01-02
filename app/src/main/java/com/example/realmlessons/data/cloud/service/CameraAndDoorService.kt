package com.example.realmlessons.data.cloud.service

import com.example.realmlessons.data.cloud.models.camera.CameraResponseResult
import com.example.realmlessons.data.cloud.models.camera.CamerasCloud
import com.example.realmlessons.data.cloud.models.door.DoorCloud
import com.example.realmlessons.data.cloud.models.door.DoorResponseResult
import retrofit2.Response
import retrofit2.http.GET

private const val CAMERA_BASE_URL = "api/rubetek/cameras/"
private const val DOOR_BASE_URL = "api/rubetek/doors/"

interface CameraAndDoorService {

    @GET(CAMERA_BASE_URL)
    suspend fun fetchCamera(
    ): Response<CameraResponseResult>

    @GET(DOOR_BASE_URL)
    suspend fun fetchDoor(): Response<DoorResponseResult>
}
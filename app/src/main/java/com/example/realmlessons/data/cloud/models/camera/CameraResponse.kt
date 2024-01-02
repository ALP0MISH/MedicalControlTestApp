package com.example.realmlessons.data.cloud.models.camera


import com.google.gson.annotations.SerializedName

data class CameraResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("success")
    val success: Boolean
) : java.io.Serializable

data class CameraResponseResult(val data: CamerasCloud)

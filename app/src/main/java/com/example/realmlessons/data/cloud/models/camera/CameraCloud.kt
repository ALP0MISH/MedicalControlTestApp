package com.example.realmlessons.data.cloud.models.camera


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("cameras")
    val cameras: List<CameraCloud>,
    @SerializedName("room")
    val room: List<String>
) : java.io.Serializable


package com.example.realmlessons.data.cloud.models.camera


import com.google.gson.annotations.SerializedName
import java.io.Serializable

private const val FAVORITE = "favorites"
private const val ID = "id"
private const val NAME = "name"
private const val REC = "rec"
private const val ROOM = "room"
private const val IMAGE_URL = "snapshot"
private const val CAMERAS = "cameras"

data class CameraCloud(
    @SerializedName(FAVORITE)
    val favorites: Boolean,
    @SerializedName(ID)
    val id: Int,
    @SerializedName(NAME)
    val name: String,
    @SerializedName(REC)
    val rec: Boolean,
    @SerializedName(ROOM)
    val room: String,
    @SerializedName(IMAGE_URL)
    val snapshot: String,
) : Serializable

data class CamerasCloud(
    @SerializedName(CAMERAS)
    val cameras: List<CameraCloud>
)

data class CameraResponseResult(val data: CamerasCloud)


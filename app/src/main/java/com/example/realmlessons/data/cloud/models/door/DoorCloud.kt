package com.example.realmlessons.data.cloud.models.door


import com.google.gson.annotations.SerializedName

private const val FAVORITE = "favorites"
private const val ID = "id"
private const val NAME = "name"
private const val ROOM = "room"
private const val SNAPSHOT = "snapshot"

data class DoorCloud(
    @SerializedName(FAVORITE) val favorites: Boolean,
    @SerializedName(ID) val id: Int,
    @SerializedName(NAME) val name: String,
    @SerializedName(ROOM) val room: String?,
    @SerializedName(SNAPSHOT) val snapshot: String?
) : java.io.Serializable

data class DoorResponseResult(val data: List<DoorCloud>)


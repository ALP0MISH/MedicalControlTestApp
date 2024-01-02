package com.example.realmlessons.data.cloud.models.door


import com.google.gson.annotations.SerializedName

data class DoorResponse(
    @SerializedName("data")
    val `data`: List<DoorCloud>,
    @SerializedName("success")
    val success: Boolean
) : java.io.Serializable
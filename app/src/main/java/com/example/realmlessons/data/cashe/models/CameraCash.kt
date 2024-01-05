package com.example.realmlessons.data.cashe.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "camera_table"
)
data class CameraCash(
    @PrimaryKey
    @ColumnInfo("id") val id: Int,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("snapshot") val snapshot: String,
    @ColumnInfo("room") val room: String,
    @ColumnInfo("favorites") val favorites: Boolean,
    @ColumnInfo("rec") val rec: Boolean
)
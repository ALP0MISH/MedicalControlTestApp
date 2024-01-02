package com.example.realmlessons.data.cashe.models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "camera_table"
)
data class CameraCash(

    @androidx.room.PrimaryKey(autoGenerate = true)
    @ColumnInfo("id") val id: Int,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("snapshot") val snapshot: String,
    @ColumnInfo("room") val room: String,
    @ColumnInfo("favorites") val favorites: Boolean,
    @ColumnInfo("rec") val rec: Boolean
)
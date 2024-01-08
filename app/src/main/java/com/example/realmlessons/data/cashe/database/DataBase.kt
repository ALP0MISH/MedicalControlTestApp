package com.example.realmlessons.data.cashe.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.realmlessons.data.cashe.dao.CameraDao
import com.example.realmlessons.data.cashe.models.CameraCash

@Database(
    entities = [CameraCash::class],
    version = 1
)
abstract class DataBase : RoomDatabase() {

    abstract fun cameraDoa(): CameraDao
}
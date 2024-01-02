package com.example.realmlessons.data.cashe.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.realmlessons.data.cashe.models.CameraCash

@Dao
interface CommonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCamera(doorCash: CameraCash)

    @Query("SELECT EXISTS(SELECT 1 FROM camera_table WHERE id = :id)")
    fun fetchSavedCameraById(id: Int): kotlinx.coroutines.flow.Flow<Boolean>

    @Query("DELETE  FROM camera_table WHERE id = :id")
    suspend fun deleteCameraById(id: Int)
}
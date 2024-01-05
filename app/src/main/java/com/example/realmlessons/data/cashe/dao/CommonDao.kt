package com.example.realmlessons.data.cashe.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.realmlessons.data.cashe.models.CameraCash
import kotlinx.coroutines.flow.Flow

@Dao
interface CommonDao {

    @Query("SELECT * FROM camera_table")
    fun observeAllSavedCamera(): Flow<List<CameraCash>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCamera(doorCash: CameraCash)

    @Query("SELECT EXISTS(SELECT 1 FROM camera_table WHERE id = :id)")
    suspend fun fetchSavedCameraById(id: Int): Boolean

    @Query("DELETE FROM camera_table WHERE id = :id")
    suspend fun deleteCameraById(id: Int)
}
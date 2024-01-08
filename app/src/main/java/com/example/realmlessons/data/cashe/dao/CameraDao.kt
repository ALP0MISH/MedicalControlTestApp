package com.example.realmlessons.data.cashe.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.realmlessons.data.cashe.models.CameraCash
import kotlinx.coroutines.flow.Flow

@Dao
interface CameraDao {

    @Query("SELECT id FROM camera")
    fun observeAllSavedCamera(): Flow<List<Int>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCamera(doorCash: CameraCash)

    @Query("SELECT EXISTS(SELECT 1 FROM camera WHERE id = :id)")
    fun fetchSavedCameraById(id: Int): Flow<Boolean>

    @Query("DELETE FROM camera WHERE id = :id")
    suspend fun deleteCameraById(id: Int)
}
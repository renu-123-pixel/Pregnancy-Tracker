package com.heartratemonitor.pregnancyvitalstrackerwithreminders.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface VitalsDao {

    @Insert
    suspend fun insertVitals(vitals: VitalsEntity)

    @Query("SELECT * FROM vitals ORDER BY timestamp DESC")
    fun getAllVitals(): Flow<List<VitalsEntity>>
}

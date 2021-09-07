package com.pascaciorc.checkpoint.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CheckpointDao {
    @Query("SELECT * from checkpoints")
    fun getCheckpoints(): Flow<List<Checkpoint>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCheckpoint(checkpoint: Checkpoint): Long
}
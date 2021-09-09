package com.pascaciorc.checkpoint.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CheckpointDao {
    @Query("SELECT * FROM checkpoints")
    fun getCheckpoints(): Flow<List<Checkpoint>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCheckpoint(checkpoint: Checkpoint): Long

    @Query("UPDATE checkpoints SET visitedDate = :date WHERE id LIKE :id")
    suspend fun updateCheckpoint(id: Long, date: Long): Int

    @Query("SELECT * FROM checkpoints WHERE id LIKE :id")
    suspend fun getCheckpoint(id: Long): Checkpoint
}
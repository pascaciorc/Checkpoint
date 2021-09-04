package com.pascaciorc.checkpoint.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CheckpointDao {
    @Query("SELECT * from checkpoints")
    fun getCheckpoints(): Flow<List<Checkpoint>>
}
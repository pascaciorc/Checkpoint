package com.pascaciorc.checkpoint.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CheckpointRepository @Inject constructor(private val dao: CheckpointDao) {

    fun getCheckpoints() = dao.getCheckpoints()

    suspend fun insertCheckpoint(checkpoint: Checkpoint) = dao.insertCheckpoint(checkpoint)
}
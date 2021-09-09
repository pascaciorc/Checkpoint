package com.pascaciorc.checkpoint.data

sealed class CheckpointState {
    class Success(val id: Long): CheckpointState()
}

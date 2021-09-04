package com.pascaciorc.checkpoint.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "checkpoints")
data class Checkpoint(
    var name: String,
    var address: String,
    var createdDate: Long,
    var visitedDate: Long,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)

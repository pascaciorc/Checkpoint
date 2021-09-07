package com.pascaciorc.checkpoint.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "checkpoints")
data class Checkpoint(
    var name: String,
    var address: String,
    var createdDate: Long,
    var visitedDate: Long,
    @Embedded(prefix = "starting_")
    var startingPoint: Coordinates,
    @Embedded(prefix = "destination_")
    var destinationPoint: Coordinates,
    @PrimaryKey(autoGenerate = true) var id: Long = 0
)

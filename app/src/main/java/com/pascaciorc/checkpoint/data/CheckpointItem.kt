package com.pascaciorc.checkpoint.data

import android.content.Context
import com.pascaciorc.checkpoint.utils.getDistance
import com.pascaciorc.checkpoint.utils.toDate

data class CheckpointItem(
    val name: String,
    val address: String,
    val distance: String,
    val createdDate: String,
    val visitedDate: String
)

fun Checkpoint.toCheckpointItem(context: Context): CheckpointItem {
    return CheckpointItem(
        this.name,
        this.address,
        context.getDistance(startingPoint.toLocation(), destinationPoint.toLocation()),
        this.createdDate.toDate(),
        if(this.visitedDate != 0L) this.visitedDate.toDate() else String()
    )
}
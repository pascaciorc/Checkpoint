package com.pascaciorc.checkpoint.data

data class Checkpoint(
    var name: String,
    var address: String,
    var createdDate: Long,
    var visitedDate: Long
)

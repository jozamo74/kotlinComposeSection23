package com.ttec.section23.addtasks.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class TaskEntity(
    @PrimaryKey val id: Long ,
    val task: String,
    var selected: Boolean = false,
)
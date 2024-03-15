package com.ttec.section23.addtasks.domain

import com.ttec.section23.addtasks.data.TaskRepository
import com.ttec.section23.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository
){
    operator fun invoke(): Flow<List<TaskModel>> {
        return taskRepository.tasks
    }
}
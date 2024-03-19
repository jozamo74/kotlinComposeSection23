package com.ttec.section23.addtasks.domain

import com.ttec.section23.addtasks.data.TaskRepository
import com.ttec.section23.addtasks.ui.model.TaskModel
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
){
    suspend operator fun invoke (taskModel: TaskModel) {
        taskRepository.delete(taskModel)
    }

}
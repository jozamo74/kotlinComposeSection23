package com.ttec.section23.addtasks.ui

import com.ttec.section23.addtasks.ui.model.TaskModel

sealed interface TasksUiState {
    data object Loading: TasksUiState
    data class Error(val throwable: Throwable)
    data class Success( val task:List<TaskModel>): TasksUiState
}
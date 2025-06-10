package com.example.modsen_tasks_oleg.ui.screen.taskList

sealed interface TaskListIntent {
    data class Navigate(val task: TaskEnum) : TaskListIntent
}
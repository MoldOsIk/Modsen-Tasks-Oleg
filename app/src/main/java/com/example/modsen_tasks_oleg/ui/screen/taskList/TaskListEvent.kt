package com.example.modsen_tasks_oleg.ui.screen.taskList

sealed interface TaskListEvent {
    data class Navigate(val task: TaskEnum) : TaskListEvent
}
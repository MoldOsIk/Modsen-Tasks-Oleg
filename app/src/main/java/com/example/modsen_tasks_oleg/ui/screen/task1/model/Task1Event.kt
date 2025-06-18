package com.example.modsen_tasks_oleg.ui.screen.task1.model

sealed interface Task1Event {
    object NavigateToSuccess : Task1Event
    data class ShowError(val message: String) : Task1Event
}

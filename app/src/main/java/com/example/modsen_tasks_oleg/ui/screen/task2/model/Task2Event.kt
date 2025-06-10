package com.example.modsen_tasks_oleg.ui.screen.task2.model

sealed interface PostsEvent {
    data class ShowError(val message: String) : PostsEvent
}

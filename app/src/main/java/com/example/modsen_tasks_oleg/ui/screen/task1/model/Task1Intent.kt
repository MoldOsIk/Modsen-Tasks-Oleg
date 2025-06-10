package com.example.modsen_tasks_oleg.ui.screen.task1.model

sealed interface Task1Intent {
    object Submit : Task1Intent
    data class UpdateLogin(val login: String) : Task1Intent
    data class UpdatePassword(val password: String) : Task1Intent
}
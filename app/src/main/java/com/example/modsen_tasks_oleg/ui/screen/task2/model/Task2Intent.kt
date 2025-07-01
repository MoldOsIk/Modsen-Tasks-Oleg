package com.example.modsen_tasks_oleg.ui.screen.task2.model

import com.example.modsen_tasks_oleg.domain.model.PostDomainModel

sealed interface Task2Intent {
    object LoadPosts : Task2Intent
    data class SelectPost(val post: PostDomainModel) : Task2Intent
    data class UpdateSearchQuery(val query: String) : Task2Intent
}
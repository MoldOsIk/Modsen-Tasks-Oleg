package com.example.modsen_tasks_oleg.ui.screen.task2.model

import com.example.modsen_tasks_oleg.data.model.PostDomainModel

sealed interface PostsIntent {
    object LoadPosts : PostsIntent
    data class SelectPost(val post: PostDomainModel) : PostsIntent
}
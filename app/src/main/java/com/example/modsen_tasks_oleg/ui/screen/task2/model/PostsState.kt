package com.example.modsen_tasks_oleg.ui.screen.task2.model

import com.example.modsen_tasks_oleg.domain.model.PostDomainModel
import com.example.modsen_tasks_oleg.domain.model.MyExceptionDomainModel

data class PostsState(
    val posts: List<PostDomainModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: MyExceptionDomainModel? = null,
    val searchQuery: String = ""
)
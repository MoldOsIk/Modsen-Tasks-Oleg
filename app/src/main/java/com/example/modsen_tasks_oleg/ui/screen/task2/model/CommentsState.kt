package com.example.modsen_tasks_oleg.ui.screen.task2.model

import com.example.modsen_tasks_oleg.domain.model.CommentDomainModel
import com.example.modsen_tasks_oleg.domain.model.MyExceptionDomainModel
import com.example.modsen_tasks_oleg.domain.model.PostDomainModel

data class CommentsState(
    val comments: List<CommentDomainModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: MyExceptionDomainModel? = null,
    val currentPost: PostDomainModel
)
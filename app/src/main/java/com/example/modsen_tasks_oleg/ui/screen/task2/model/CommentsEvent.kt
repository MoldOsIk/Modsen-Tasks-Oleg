package com.example.modsen_tasks_oleg.ui.screen.task2.model

import com.example.modsen_tasks_oleg.domain.model.MyExceptionDomainModel

sealed interface CommentsEvent {
    data class ShowError(val exception: MyExceptionDomainModel) : CommentsEvent
}
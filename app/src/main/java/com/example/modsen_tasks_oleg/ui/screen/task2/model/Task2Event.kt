package com.example.modsen_tasks_oleg.ui.screen.task2.model

import com.example.modsen_tasks_oleg.domain.model.MyExceptionDomainModel
import com.example.modsen_tasks_oleg.domain.model.PostDomainModel
import com.example.modsen_tasks_oleg.ui.screen.taskList.TaskEnum
import com.example.modsen_tasks_oleg.ui.screen.taskList.TaskListIntent

sealed interface Task2Event {
    data class ShowError(val exception: MyExceptionDomainModel) : Task2Event
    data class SelectPost(val post: PostDomainModel) : Task2Event
}

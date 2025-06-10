package com.example.modsen_tasks_oleg.ui.screen.taskList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modsen_tasks_oleg.ui.util.SingleFlowEvent
import kotlinx.coroutines.launch

class TaskListViewModel : ViewModel() {
    private val _event = SingleFlowEvent<TaskListEvent>(viewModelScope)
    val event = _event.flow

    val tasks = TaskEnum.entries

    fun processIntent(intent: TaskListIntent) {
        when (intent) {
            is TaskListIntent.Navigate -> {
                viewModelScope.launch {
                    _event.emit(TaskListEvent.Navigate(intent.task))
                }
            }
        }
    }
}
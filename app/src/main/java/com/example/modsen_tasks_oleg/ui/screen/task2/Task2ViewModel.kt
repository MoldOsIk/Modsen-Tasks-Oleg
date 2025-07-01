package com.example.modsen_tasks_oleg.ui.screen.task2

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modsen_tasks_oleg.domain.model.PostDomainModel
import com.example.modsen_tasks_oleg.domain.model.TResult
import com.example.modsen_tasks_oleg.domain.usecase.GetPostsUseCase
import com.example.modsen_tasks_oleg.ui.screen.task2.model.Task2Intent
import com.example.modsen_tasks_oleg.ui.screen.task2.model.PostsState
import com.example.modsen_tasks_oleg.ui.screen.task2.model.Task2Event
import com.example.modsen_tasks_oleg.ui.util.SingleFlowEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class Task2ViewModel(
    private val getPostsUseCase: GetPostsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(PostsState())
    val state: StateFlow<PostsState> = _state.asStateFlow()

    private val _event = SingleFlowEvent<Task2Event>(viewModelScope)
    val event = _event.flow

    private var originalPosts: List<PostDomainModel> = emptyList()

    init {
        loadPosts()
    }

    fun processIntent(intent: Task2Intent) {
        when (intent) {
            is Task2Intent.LoadPosts -> {
                loadPosts()
            }
            is Task2Intent.SelectPost -> {
                viewModelScope.launch {
                    _event.emit(Task2Event.SelectPost(intent.post))
                }
            }
            is Task2Intent.UpdateSearchQuery -> {
                _state.update { currentState ->
                    currentState.copy(searchQuery = intent.query)
                }
                filterPosts()
            }
        }
    }

    private fun loadPosts() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            when (val result = getPostsUseCase()) {
                is TResult.Success -> {
                    originalPosts = result.data
                    _state.update { it.copy(posts = result.data, isLoading = false) }
                    filterPosts()
                }
                is TResult.Error -> {
                    _state.update { it.copy(isLoading = false, error = result.exception) }
                    _event.emit(Task2Event.ShowError(result.exception))
                }
            }
        }
    }

    private fun filterPosts() {
        val currentQuery = _state.value.searchQuery
        val filteredPosts = if (currentQuery.isEmpty()) {
            originalPosts
        } else {
            originalPosts.filter {
                it.title.contains(currentQuery, ignoreCase = true) ||
                        it.body.contains(currentQuery, ignoreCase = true)
            }
        }
        _state.update { it.copy(posts = filteredPosts) }
    }
}
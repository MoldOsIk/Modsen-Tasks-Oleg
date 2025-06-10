package com.example.modsen_tasks_oleg.ui.screen.task2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modsen_tasks_oleg.domain.model.TResult
import com.example.modsen_tasks_oleg.domain.usecase.GetPostsUseCase
import com.example.modsen_tasks_oleg.ui.screen.task2.model.PostsEvent
import com.example.modsen_tasks_oleg.ui.screen.task2.model.PostsIntent
import com.example.modsen_tasks_oleg.ui.screen.task2.model.PostsState
import com.example.modsen_tasks_oleg.ui.util.SingleFlowEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostsViewModel(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(PostsState())
    val state: StateFlow<PostsState> = _state.asStateFlow()

    private val _event = SingleFlowEvent<PostsEvent>(viewModelScope)
    val event = _event.flow

    fun processIntent(intent: PostsIntent) {
        when (intent) {
            is PostsIntent.LoadPosts -> {
                viewModelScope.launch {
                    _state.update { it.copy(isLoading = true, error = null) }
                    when (val result = getPostsUseCase()) {
                        is TResult.Success -> {
                            _state.update { it.copy(posts = result.data, isLoading = false) }
                        }
                        is TResult.Error -> {
                            _state.update { it.copy(isLoading = false, error = result.exception) }
                            _event.emit(PostsEvent.ShowError(result.exception.parseToString()))
                        }
                    }
                }
            }
            is PostsIntent.SelectPost -> {

            }
        }
    }
}
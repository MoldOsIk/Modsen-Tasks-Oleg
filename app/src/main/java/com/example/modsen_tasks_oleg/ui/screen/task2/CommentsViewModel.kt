package com.example.modsen_tasks_oleg.ui.screen.task2

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modsen_tasks_oleg.domain.model.CommentDomainModel
import com.example.modsen_tasks_oleg.domain.model.MyExceptionDomainModel
import com.example.modsen_tasks_oleg.domain.model.PostDomainModel
import com.example.modsen_tasks_oleg.domain.model.TResult
import com.example.modsen_tasks_oleg.domain.usecase.GetCommentsUseCase
import com.example.modsen_tasks_oleg.ui.screen.task2.model.CommentsEvent
import com.example.modsen_tasks_oleg.ui.screen.task2.model.CommentsState
import com.example.modsen_tasks_oleg.ui.util.SingleFlowEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CommentsViewModel(
    private val getCommentsUseCase: GetCommentsUseCase,
    private val post: PostDomainModel
) : ViewModel() {
    private val _state = MutableStateFlow(CommentsState(currentPost = post))
    val state: StateFlow<CommentsState> = _state.asStateFlow()

    private val _event = SingleFlowEvent<CommentsEvent>(viewModelScope)
    val event = _event.flow

    init {
        loadComments(post.id)
    }

    private fun loadComments(postId: Int) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            when (val result = getCommentsUseCase(postId)) {
                is TResult.Success ->
                    _state.update { it.copy(isLoading = false, comments = result.data)
                    }
                is TResult.Error ->
                    _state.update { it.copy(isLoading = false, error = result.exception)
                    }
            }
        }
    }
}
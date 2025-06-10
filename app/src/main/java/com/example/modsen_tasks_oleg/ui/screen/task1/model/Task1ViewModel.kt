package com.example.modsen_tasks_oleg.ui.screen.task1.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modsen_tasks_oleg.domain.usecase.LoginUseCase
import com.example.modsen_tasks_oleg.ui.util.SingleFlowEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class Task1ViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(Task1State())
    val state: StateFlow<Task1State> = _state.asStateFlow()

    private val _event = SingleFlowEvent<Task1Event>(viewModelScope)
    val event = _event.flow

    fun processIntent(intent: Task1Intent) {
        when (intent) {
            is Task1Intent.Submit -> {
                viewModelScope.launch {
                    val login = state.value.login
                    val password = state.value.password
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                    val result = loginUseCase(login, password)
                    result.fold(
                        onSuccess = {
                            _event.emit(Task1Event.NavigateToSuccess)
                            _state.update { it.copy(isLoading = false) }
                        },
                        onFailure = { e ->
                            _event.emit(Task1Event.ShowError(e.message ?: "Invalid credentials"))
                            _state.update { it.copy(isLoading = false) }
                        }
                    )
                }
            }
            is Task1Intent.UpdateLogin -> {
                _state.update { it.copy(login = intent.login) }
            }
            is Task1Intent.UpdatePassword -> {
                _state.update { it.copy(password = intent.password) }
            }
        }
    }
}
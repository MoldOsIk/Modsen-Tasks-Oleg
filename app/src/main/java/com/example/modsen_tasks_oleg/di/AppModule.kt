package com.example.modsen_tasks_oleg.di

import com.example.modsen_tasks_oleg.data.repository.AuthRepositoryImpl
import com.example.modsen_tasks_oleg.domain.repository.IAuthRepository
import com.example.modsen_tasks_oleg.domain.usecase.LoginUseCase
import com.example.modsen_tasks_oleg.ui.screen.task1.model.Task1ViewModel
import com.example.modsen_tasks_oleg.ui.screen.taskList.TaskListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
    single<IAuthRepository> { AuthRepositoryImpl() }
    single { LoginUseCase(get()) }
    viewModel { TaskListViewModel() }
    viewModel { Task1ViewModel(get()) }
}
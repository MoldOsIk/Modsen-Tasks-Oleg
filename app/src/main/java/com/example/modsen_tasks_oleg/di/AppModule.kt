package com.example.modsen_tasks_oleg.di

import com.example.modsen_tasks_oleg.data.remote.IPostsApi
import com.example.modsen_tasks_oleg.data.repository.AuthRepositoryImpl
import com.example.modsen_tasks_oleg.data.repository.PostsRepositoryImpl
import com.example.modsen_tasks_oleg.domain.repository.IAuthRepository
import com.example.modsen_tasks_oleg.domain.repository.IPostsRepository
import com.example.modsen_tasks_oleg.domain.usecase.GetPostsUseCase
import com.example.modsen_tasks_oleg.domain.usecase.LoginUseCase
import com.example.modsen_tasks_oleg.ui.screen.task1.model.Task1ViewModel
import com.example.modsen_tasks_oleg.ui.screen.task2.PostsViewModel
import com.example.modsen_tasks_oleg.ui.screen.taskList.TaskListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    viewModel { PostsViewModel(get()) }
    single<IAuthRepository> { AuthRepositoryImpl() }
    single<IPostsRepository> { PostsRepositoryImpl(get()) }
    single { LoginUseCase(get()) }
    single { GetPostsUseCase(get()) }
    single<IPostsApi> {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IPostsApi::class.java)
    }
}
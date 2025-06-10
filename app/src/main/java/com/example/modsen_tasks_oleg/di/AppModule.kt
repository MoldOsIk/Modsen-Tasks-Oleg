package com.example.modsen_tasks_oleg.di

import com.example.modsen_tasks_oleg.data.remote.IPostsApi
import com.example.modsen_tasks_oleg.data.repository.PostsRepositoryImpl
import com.example.modsen_tasks_oleg.domain.repository.IPostsRepository
import com.example.modsen_tasks_oleg.domain.usecase.GetPostsUseCase
import com.example.modsen_tasks_oleg.ui.screen.task2.PostsViewModel
import com.example.modsen_tasks_oleg.ui.screen.taskList.TaskListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val AppModule = module {
    viewModel { TaskListViewModel() }
    viewModel { PostsViewModel(get()) }
    single<IPostsRepository> { PostsRepositoryImpl(get()) }
    single { GetPostsUseCase(get()) }
    single<IPostsApi> {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IPostsApi::class.java)
    }

}
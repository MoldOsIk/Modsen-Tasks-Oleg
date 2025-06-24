package com.example.modsen_tasks_oleg.data.repository

import com.example.modsen_tasks_oleg.data.remote.IPostsApi
import com.example.modsen_tasks_oleg.domain.model.MyExceptionDomainModel
import com.example.modsen_tasks_oleg.data.model.PostDomainModel
import com.example.modsen_tasks_oleg.domain.model.TResult
import com.example.modsen_tasks_oleg.domain.repository.IPostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import kotlin.collections.map
import kotlin.getOrElse
import kotlin.runCatching


class PostsRepositoryImpl(
    private val api: IPostsApi
) : IPostsRepository {
    override suspend fun getPosts(): TResult<List<PostDomainModel>, MyExceptionDomainModel> =
        withContext(Dispatchers.IO) {
            runCatching {
                val result = api.getPosts()
                val mappedResult = result.map { it.toDomainModel() }
                TResult.Success<List<PostDomainModel>, MyExceptionDomainModel>(mappedResult)
            }.getOrElse {
                TResult.Error(it.toMyExceptionDomainModel())
            }
        }
}

fun Throwable.toMyExceptionDomainModel(): MyExceptionDomainModel {
    return when (this) {
        is UnknownHostException, is ConnectException -> MyExceptionDomainModel.NoInternet(this)
        is HttpException -> {
            when (this.code()) {
                else -> MyExceptionDomainModel.Other(this)
            }
        }
        is MyExceptionDomainModel -> this
        else -> MyExceptionDomainModel.Other(this)
    }
}
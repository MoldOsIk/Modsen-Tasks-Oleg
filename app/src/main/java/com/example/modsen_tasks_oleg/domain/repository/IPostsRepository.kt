package com.example.modsen_tasks_oleg.domain.repository

import com.example.modsen_tasks_oleg.data.model.PostDomainModel
import com.example.modsen_tasks_oleg.domain.model.MyExceptionDomainModel
import com.example.modsen_tasks_oleg.domain.model.TResult

interface IPostsRepository {
    suspend fun getPosts(): TResult<List<PostDomainModel>, MyExceptionDomainModel>
}
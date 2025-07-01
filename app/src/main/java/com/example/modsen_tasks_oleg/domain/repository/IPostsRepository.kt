package com.example.modsen_tasks_oleg.domain.repository

import com.example.modsen_tasks_oleg.domain.model.CommentDomainModel
import com.example.modsen_tasks_oleg.domain.model.PostDomainModel
import com.example.modsen_tasks_oleg.domain.model.MyExceptionDomainModel
import com.example.modsen_tasks_oleg.domain.model.TResult

interface IPostsRepository {
    suspend fun getPosts(): TResult<List<PostDomainModel>, MyExceptionDomainModel>
    suspend fun getComments(postId: Int): TResult<List<CommentDomainModel>, MyExceptionDomainModel>
}
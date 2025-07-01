package com.example.modsen_tasks_oleg.domain.usecase

import com.example.modsen_tasks_oleg.domain.model.CommentDomainModel
import com.example.modsen_tasks_oleg.domain.model.MyExceptionDomainModel
import com.example.modsen_tasks_oleg.domain.model.TResult
import com.example.modsen_tasks_oleg.domain.repository.IPostsRepository

class GetCommentsUseCase(private val repository: IPostsRepository) {
    suspend operator fun invoke(postId: Int): TResult<List<CommentDomainModel>, MyExceptionDomainModel> = repository.getComments(postId)
}
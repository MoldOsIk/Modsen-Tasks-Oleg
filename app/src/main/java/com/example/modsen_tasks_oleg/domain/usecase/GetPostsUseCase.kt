package com.example.modsen_tasks_oleg.domain.usecase

import com.example.modsen_tasks_oleg.data.model.PostDomainModel
import com.example.modsen_tasks_oleg.domain.model.MyExceptionDomainModel
import com.example.modsen_tasks_oleg.domain.model.TResult
import com.example.modsen_tasks_oleg.domain.repository.IPostsRepository

class GetPostsUseCase(
    private val repository: IPostsRepository
) {
    suspend operator fun invoke(): TResult<List<PostDomainModel>, MyExceptionDomainModel> = repository.getPosts()
}
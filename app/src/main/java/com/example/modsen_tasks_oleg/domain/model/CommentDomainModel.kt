package com.example.modsen_tasks_oleg.domain.model

data class CommentDomainModel(
    val id: Int,
    val postId: Int,
    val name: String,
    val email: String,
    val body: String
)
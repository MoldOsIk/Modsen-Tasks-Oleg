package com.example.modsen_tasks_oleg.data.model

import com.example.modsen_tasks_oleg.domain.model.PostDomainModel
import com.google.gson.annotations.SerializedName

data class PostApiModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String
)
{
    fun toDomainModel() = PostDomainModel(
        id = id,
        userId = userId,
        title = title,
        body = body
    )
}
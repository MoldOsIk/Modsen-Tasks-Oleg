package com.example.modsen_tasks_oleg.data.model

import com.example.modsen_tasks_oleg.domain.model.CommentDomainModel
import com.google.gson.annotations.SerializedName

data class CommentApiModel(
    @SerializedName("id") val id: Int,
    @SerializedName("postId") val postId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("body") val body: String
) {
    fun toDomainModel() = CommentDomainModel(
        id = id,
        postId = postId,
        name = name,
        email = email,
        body = body
    )
}
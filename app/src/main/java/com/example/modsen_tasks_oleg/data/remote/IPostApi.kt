package com.example.modsen_tasks_oleg.data.remote

import com.example.modsen_tasks_oleg.data.model.CommentApiModel
import com.example.modsen_tasks_oleg.data.model.PostApiModel
import retrofit2.http.GET
import retrofit2.http.Query

interface IPostsApi {
    @GET("posts")
    suspend fun getPosts(): List<PostApiModel>

    @GET("comments")
    suspend fun getComments(@Query("postId") postId: Int): List<CommentApiModel>
}
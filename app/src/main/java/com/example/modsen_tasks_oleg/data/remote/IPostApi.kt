package com.example.modsen_tasks_oleg.data.remote

import com.example.modsen_tasks_oleg.data.model.PostApiModel
import retrofit2.http.GET

interface IPostsApi {
    @GET("posts")
    suspend fun getPosts(): List<PostApiModel>
}
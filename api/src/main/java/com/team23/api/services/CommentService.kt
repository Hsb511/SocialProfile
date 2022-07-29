package com.team23.api.services

import com.team23.api.DummyApi
import com.team23.api.models.CommentDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface CommentService {
    @GET("post/{postId}/comment")
    @Headers("app-id: ${DummyApi.APP_ID}")
    suspend fun getCommentsByPost(@Path("postId") postId: String): Response<List<CommentDTO>>
}
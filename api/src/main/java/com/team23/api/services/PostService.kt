package com.team23.api.services

import com.team23.api.DummyApi
import com.team23.api.models.Page
import com.team23.api.models.PostDTO
import com.team23.api.models.PostPreviewDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface PostService {
    @GET("post?limit=10")
    @Headers("app-id: ${DummyApi.APP_ID}")
    suspend fun getPosts(@Query("page") page: UInt): Response<Page<PostPreviewDTO>>

    @GET("post/{id}")
    @Headers("app-id: ${DummyApi.APP_ID}")
    suspend fun getPost(@Path("id") id: String): Response<PostDTO>
}
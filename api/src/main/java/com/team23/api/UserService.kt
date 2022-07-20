package com.team23.api

import com.team23.api.models.Page
import com.team23.api.models.PostPreview
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UserService {


    @GET("/post?limit=100")
    @Headers("app-id: ${DummyApi.APP_ID}")
    suspend fun getPosts(@Query("page") page: UInt): Response<Page<PostPreview>>
}
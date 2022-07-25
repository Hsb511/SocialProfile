package com.team23.api

import com.team23.api.models.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface UserService {

    @GET("user/{id}")
    @Headers("app-id: ${DummyApi.APP_ID}")
    suspend fun getUserData(@Path("id") id: String): Response<User>
}
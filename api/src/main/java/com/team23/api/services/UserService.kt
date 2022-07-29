package com.team23.api.services

import com.team23.api.DummyApi
import com.team23.api.models.UserDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface UserService {

    @GET("user/{id}")
    @Headers("app-id: ${DummyApi.APP_ID}")
    suspend fun getUserData(@Path("id") id: String): Response<UserDTO>
}
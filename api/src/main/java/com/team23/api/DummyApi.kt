package com.team23.api

import com.team23.api.services.CommentService
import com.team23.api.services.PostService
import com.team23.api.services.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DummyApi {
    companion object {
        const val APP_ID = ""
    }

    val commentService: CommentService
    val postService: PostService
    val userService: UserService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyapi.io/data/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        commentService = retrofit.create(CommentService::class.java)
        postService = retrofit.create(PostService::class.java)
        userService = retrofit.create(UserService::class.java)
    }

}
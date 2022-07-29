package com.team23.home.data.repositories

import com.team23.api.services.PostService
import com.team23.home.data.mappers.toModel
import com.team23.home.domain.models.PostModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postService: PostService
) : PostRepository {
    override suspend fun getPosts(): List<PostModel> =
        withContext(Dispatchers.IO) { postService.getPosts(1u) }.let { response ->
            if (response.isSuccessful) {
                response.body()!!.data.map { it.toModel(Dispatchers.IO) }
            } else {
                emptyList()
            }
        }
}
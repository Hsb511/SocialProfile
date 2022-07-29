package com.team23.post.data.repositories

import com.team23.api.services.PostService
import com.team23.post.data.mappers.toModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostRepositoryImpl@Inject constructor(
    private val postService: PostService
) : PostRepository {
    override suspend fun getPostById(postId: String) =
        withContext(Dispatchers.IO) { postService.getPost(postId) }.let { response ->
            if (response.isSuccessful) {
                response.body()?.toModel(Dispatchers.IO)
            } else {
                null
            }
        }
}
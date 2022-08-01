package com.team23.post.data.repositories

import com.team23.api.services.CommentService
import com.team23.post.data.mappers.toModel
import com.team23.post.domain.models.CommentModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val commentService: CommentService
): CommentRepository {
    override suspend fun getCommentsByPostId(postId: String): List<CommentModel> =
        withContext(Dispatchers.IO) { commentService.getCommentsByPost(postId) }.let { response ->
            if (response.isSuccessful) {
                response.body()!!.data.map { it.toModel() }
            } else {
                emptyList()
            }
        }

}
package com.team23.post.data.repositories

import com.team23.post.domain.models.CommentModel

interface CommentRepository {
    suspend fun getCommentsByPostId(postId: String): List<CommentModel>
}
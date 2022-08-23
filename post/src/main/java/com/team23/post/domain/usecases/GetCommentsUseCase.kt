package com.team23.post.domain.usecases

import com.team23.post.data.repositories.CommentRepository
import com.team23.post.domain.models.CommentModel
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(
    private val commentRepository: CommentRepository
) {
    suspend fun execute(postId: String): List<CommentModel> =
        commentRepository.getCommentsByPostId(postId)

}
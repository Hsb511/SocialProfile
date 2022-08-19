package com.team23.post.domain.usecases

import com.team23.core.extensions.toDuration
import com.team23.post.data.repositories.CommentRepository
import com.team23.post.ui.viewobjects.CommentVO
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(
    private val commentRepository: CommentRepository
) {
    suspend fun execute(postId: String): List<CommentVO> =
        commentRepository.getCommentsByPostId(postId).map {
            CommentVO(
                id = it.id,
                userPictureUrl = it.userPictureUrl,
                username = it.username,
                userId = it.userId,
                duration = it.commentDate.toDuration(),
                text = it.commentText
            )
        }
}
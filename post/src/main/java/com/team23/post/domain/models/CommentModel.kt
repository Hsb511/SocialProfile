package com.team23.post.domain.models

import java.time.LocalDateTime

data class CommentModel(
    val id: String,
    val userPictureUrl: String,
    val username: String,
    val userId: String,
    val commentDate: LocalDateTime,
    val commentText: String
)

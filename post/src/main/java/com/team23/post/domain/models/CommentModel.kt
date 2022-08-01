package com.team23.post.domain.models

import android.graphics.Bitmap
import java.time.LocalDateTime

data class CommentModel(
    val userPicture: Bitmap?,
    val username: String,
    val userId: String,
    val commentDate: LocalDateTime,
    val commentText: String
)

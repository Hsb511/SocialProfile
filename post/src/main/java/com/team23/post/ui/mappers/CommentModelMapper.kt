package com.team23.post.ui.mappers

import com.team23.core.extensions.toDuration
import com.team23.post.domain.models.CommentModel
import com.team23.post.ui.viewobjects.CommentVO

fun CommentModel.toVO() = CommentVO(
    id = this.id,
    userPictureUrl = this.userPictureUrl,
    username = this.username,
    userId = this.userId,
    duration = this.commentDate.toDuration(),
    text = this.commentText
)
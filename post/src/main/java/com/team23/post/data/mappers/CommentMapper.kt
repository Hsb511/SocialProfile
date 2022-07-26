package com.team23.post.data.mappers

import com.team23.api.models.CommentDTO
import com.team23.core.extensions.fromISO8601
import com.team23.post.domain.models.CommentModel

fun CommentDTO.toModel() = CommentModel(
    id = id,
    userPictureUrl = owner.picture,
    username = "${owner.firstName} ${owner.lastName}",
    userId = owner.id,
    commentDate = publishDate.fromISO8601()!!,
    commentText = message
)
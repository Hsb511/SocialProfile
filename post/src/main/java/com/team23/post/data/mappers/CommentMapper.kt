package com.team23.post.data.mappers

import com.team23.api.models.CommentDTO
import com.team23.core.extensions.downloadBitmap
import com.team23.core.extensions.fromISO8601
import com.team23.core.extensions.toRoundBitmap
import com.team23.post.domain.models.CommentModel
import kotlinx.coroutines.Dispatchers

suspend fun CommentDTO.toModel() = CommentModel(
    id = id,
    userPictureUrl = owner.picture,
    username = "${owner.firstName} ${owner.lastName}",
    userId = owner.id,
    commentDate = publishDate.fromISO8601(),
    commentText = message
)
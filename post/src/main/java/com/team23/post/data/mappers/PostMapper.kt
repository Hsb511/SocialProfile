package com.team23.post.data.mappers

import com.team23.api.models.PostDTO
import com.team23.core.extensions.downloadBitmap
import com.team23.core.extensions.fromISO8601toReadableDateTime
import com.team23.core.extensions.toRoundBitmap
import com.team23.post.domain.models.PostModel
import kotlinx.coroutines.CoroutineDispatcher

suspend fun PostDTO.toModel(dispatcher: CoroutineDispatcher) = PostModel(
    id = id,
    text = text,
    image = image.downloadBitmap(dispatcher),
    likes = likes,
    link = link,
    tags = tags,
    publishDate = publishDate.fromISO8601toReadableDateTime(),
    ownerId = owner.id,
    ownerName = "${owner.firstName} ${owner.lastName}",
    ownerPicture = owner.picture.downloadBitmap(dispatcher)?.toRoundBitmap()
)
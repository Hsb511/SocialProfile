package com.team23.post.data.mappers

import com.team23.api.models.PostDTO
import com.team23.core.extensions.fromISO8601toReadableDateTime
import com.team23.post.domain.models.PostModel

fun PostDTO.toModel() = PostModel(
    id = id,
    text = text,
    image = image,
    likes = likes,
    link = link,
    tags = tags,
    publishDate = publishDate.fromISO8601toReadableDateTime(),
    ownerId = owner.id,
    ownerName = "${owner.firstName} ${owner.lastName}",
    ownerPicture = owner.picture
)
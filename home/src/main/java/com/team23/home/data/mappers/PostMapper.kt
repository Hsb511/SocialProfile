package com.team23.home.data.mappers

import com.team23.api.models.PostPreviewDTO
import com.team23.core.extensions.downloadBitmap
import com.team23.core.extensions.fromISO8601toReadableDateTime
import com.team23.core.extensions.toRoundBitmap
import com.team23.home.domain.models.PostModel
import kotlinx.coroutines.CoroutineDispatcher

fun PostPreviewDTO.toModel() =
    PostModel(
        id = id,
        text = text,
        imageUrl = image,
        likes = likes,
        tags = tags,
        publishDate = publishDate.fromISO8601toReadableDateTime(),
        ownerId = owner.id,
        ownerName = "${owner.firstName} ${owner.lastName}",
        ownerPictureUrl = owner.picture
    )


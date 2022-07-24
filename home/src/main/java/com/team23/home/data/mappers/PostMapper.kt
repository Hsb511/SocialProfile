package com.team23.home.data.mappers

import com.team23.api.models.PostPreview
import com.team23.home.data.extensions.downloadBitmap
import com.team23.home.data.extensions.fromISO8601toReadableDateTime
import com.team23.home.data.extensions.toRoundBitmap
import com.team23.home.domain.models.PostModel
import kotlinx.coroutines.CoroutineDispatcher

suspend fun PostPreview.toModel(
    dispatcher: CoroutineDispatcher
) =
    PostModel(
        text = text,
        image = image.downloadBitmap(dispatcher),
        likes = likes,
        tags = tags,
        publishDate = publishDate.fromISO8601toReadableDateTime(),
        ownerId = owner.id,
        ownerName = "${owner.firstName} ${owner.lastName}",
        ownerPicture = owner.picture.downloadBitmap(dispatcher)?.toRoundBitmap()
    )


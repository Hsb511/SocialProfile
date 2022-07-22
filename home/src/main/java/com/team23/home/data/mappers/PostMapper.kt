package com.team23.home.data.mappers

import com.team23.api.models.PostPreview
import com.team23.home.domain.models.PostModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val ISO_8601_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

fun PostPreview.toModel() =
    PostModel(
        text = text,
        image = image,
        likes = likes,
        tags = tags,
        publishDate = LocalDateTime
            .parse(publishDate, DateTimeFormatter.ofPattern(ISO_8601_FORMAT)),
        ownerId = owner.id,
        ownerName = "${owner.firstName} ${owner.lastName}"
    )


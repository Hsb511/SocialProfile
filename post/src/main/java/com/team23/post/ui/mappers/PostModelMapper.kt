package com.team23.post.ui.mappers

import com.team23.post.domain.models.PostModel
import com.team23.post.ui.viewobjects.PostVO

fun PostModel.toVO() = PostVO(
    postPicture = this.image,
    userPicture = this.ownerPicture,
    username = this.ownerName,
    userId = this.ownerId,
    postDate = this.publishDate,
    postDescription = this.text,
    likesAmount = this.likes,
    tags = this.tags
)
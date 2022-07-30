package com.team23.post.domain.usecases

import com.team23.post.data.repositories.PostRepository
import com.team23.post.ui.viewobjects.PostVO
import javax.inject.Inject

class GetPostDataUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend fun execute(postId: String): PostVO? = postRepository.getPostById(postId)?.let {
        PostVO(
            postPicture = it.image,
            userPicture = it.ownerPicture,
            username = it.ownerName,
            userId = it.ownerId,
            postDate = it.publishDate,
            postDescription = it.text,
            likesAmount = it.likes,
            commentsAmount = 23, // TODO CHANGE THAT AFTER GETTING COMMENTS LIST
            tagsAmount = it.tags.size
        )
    }
}
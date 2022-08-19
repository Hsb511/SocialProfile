package com.team23.home.data.repositories

import com.team23.api.services.PostService
import com.team23.home.data.mappers.toModel
import com.team23.home.domain.models.PostModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postService: PostService
) : PostRepository {
    override fun getPosts(): Flow<List<PostModel>> = flow {
        val postModels = withContext(Dispatchers.IO) { postService.getPosts(0u) }.let { response ->
            if (response.isSuccessful) {
                response.body()!!.data.map { it.toModel() }
            } else {
                emptyList()
            }
        }
        emit(postModels)
    }.flowOn(Dispatchers.IO)
}
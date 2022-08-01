package com.team23.post.data.modules

import com.team23.post.data.repositories.CommentRepository
import com.team23.post.data.repositories.CommentRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CommentRepositoryModule {

    @Binds
    abstract fun bindCommentRepository(commentRepositoryImpl: CommentRepositoryImpl): CommentRepository
}
package com.team23.post

import com.team23.post.domain.models.CommentModel
import com.team23.post.ui.mappers.toVO
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDateTime

class CommentModelMapperTest {
    @Test
    fun `given empty commentModel, when toVO is called, then returns empty VO`() {
        // GIVEN
        val emptyComment = CommentModel(
            id = "",
            userPictureUrl = "",
            username = "",
            userId = "",
            commentDate = LocalDateTime.now(),
            commentText = ""
        )

        // WHEN
        val emptyCommentVO = emptyComment.toVO()

        // THEN
        assertEquals("", emptyCommentVO.id)
        assertEquals("", emptyCommentVO.userPictureUrl)
        assertEquals("", emptyCommentVO.username)
        assertEquals("", emptyCommentVO.userId)
        assertEquals("0 s", emptyCommentVO.duration)
        assertEquals("", emptyCommentVO.text)
    }

    @Test
    fun `given full commentModel, when toVO is called, then returns full VO`() {
        // GIVEN
        val fullComment = CommentModel(
            id = "23",
            userPictureUrl = "https://nice_face.png",
            username = "Chewi Solo",
            userId = "92",
            commentDate = LocalDateTime.parse("1999-08-23T12:30:54"),
            commentText = "This is a nice pic!"
        )

        // WHEN
        val fullCommentVO = fullComment.toVO()

        // THEN
        assertEquals("23", fullCommentVO.id)
        assertEquals("https://nice_face.png", fullCommentVO.userPictureUrl)
        assertEquals("Chewi Solo", fullCommentVO.username)
        assertEquals("92", fullCommentVO.userId)
        assertEquals("23 a", fullCommentVO.duration)
        assertEquals("This is a nice pic!", fullCommentVO.text)
    }
}
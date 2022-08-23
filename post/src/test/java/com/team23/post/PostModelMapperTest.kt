package com.team23.post

import com.team23.post.domain.models.PostModel
import com.team23.post.ui.mappers.toVO
import org.junit.Assert.assertEquals
import org.junit.Test

class PostModelMapperTest {

    @Test
    fun `given empty postModel, when toVO is called, then returns empty VO`() {
        // GIVEN
        val emptyPostModel = PostModel(
            id = "",
            text = "",
            image = "",
            likes = -1,
            link = "",
            tags = emptyList(),
            publishDate = "",
            ownerId = "",
            ownerName = "",
            ownerPicture = ""
        )

        // WHEN
        val emptyPostVO = emptyPostModel.toVO()

        // THEN
        assertEquals("", emptyPostVO.postPicture)
        assertEquals("", emptyPostVO.userPicture)
        assertEquals("", emptyPostVO.username)
        assertEquals("", emptyPostVO.userId)
        assertEquals("", emptyPostVO.postDate)
        assertEquals("", emptyPostVO.postDescription)
        assertEquals(-1, emptyPostVO.likesAmount)
        assertEquals(emptyList<String>(), emptyPostVO.tags)
    }

    @Test
    fun `given valid postModel, when toVO is called, then returns corresponding VO`() {
        // GIVEN
        val fullPostModel = PostModel(
            id = "23",
            text = "Cute dog",
            image = "https://nice_pic.jpg",
            likes = 23,
            link = "https://nice_link.html",
            tags = listOf("dog", "pet"),
            publishDate = "23/02/1968",
            ownerId = "42",
            ownerName = "Mark Twaine",
            ownerPicture = "https://nice_face.html"
        )

        // WHEN
        val fullPostVO = fullPostModel.toVO()

        // THEN
        assertEquals("https://nice_pic.jpg", fullPostVO.postPicture)
        assertEquals("https://nice_face.html", fullPostVO.userPicture)
        assertEquals("Mark Twaine", fullPostVO.username)
        assertEquals("42", fullPostVO.userId)
        assertEquals("23/02/1968", fullPostVO.postDate)
        assertEquals("Cute dog", fullPostVO.postDescription)
        assertEquals(23, fullPostVO.likesAmount)
        assertEquals(listOf("dog", "pet"), fullPostVO.tags)
    }
}
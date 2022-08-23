package com.team23.post

import com.team23.api.models.PostDTO
import com.team23.api.models.UserPreviewDTO
import com.team23.post.data.mappers.toModel
import org.junit.Assert.assertEquals
import org.junit.Test

class PostDTOMapperTest {
    @Test
    fun `given empty postDTO, when toModel is called, then returns empty model`() {
        // WHEN
        val emptyPostDTO = PostDTO(
            id = "",
            text = "",
            image = "",
            likes = 0,
            link = null,
            tags = emptyList(),
            publishDate = "",
            owner = UserPreviewDTO(
                id = "",
                title = "",
                firstName = "",
                lastName = "",
                picture = ""
            )
        )

        // WHEN
        val emptyPost = emptyPostDTO.toModel()

        // THEN
        assertEquals("" , emptyPost.id)
        assertEquals("" , emptyPost.text)
        assertEquals("" , emptyPost.image)
        assertEquals(0 , emptyPost.likes)
        assertEquals(null , emptyPost.link)
        assertEquals(emptyList<String>() , emptyPost.tags)
        assertEquals("" , emptyPost.publishDate)
        assertEquals("" , emptyPost.ownerId)
        assertEquals(" " , emptyPost.ownerName)
        assertEquals("" , emptyPost.ownerPicture)
    }

    @Test
    fun `given full postDTO, when toModel is called, then returns full model`() {
        // WHEN
        val fullPostDTO = PostDTO(
            id = "23",
            text = "This is a white dog",
            image = "https://dog_pic.jpg",
            likes = 23,
            link = "https://dog_pic.html",
            tags = listOf("dog", "white", "pet"),
            publishDate = "2022-08-23T23:23:23.529Z",
            owner = UserPreviewDTO(
                id = "32",
                title = "Mr",
                firstName = "Super",
                lastName = "Star",
                picture = "https://nice_face.com/pic.jpg"
            )
        )

        // WHEN
        val fullPost = fullPostDTO.toModel()

        // THEN
        assertEquals("23" , fullPost.id)
        assertEquals("This is a white dog" , fullPost.text)
        assertEquals("https://dog_pic.jpg" , fullPost.image)
        assertEquals(23 , fullPost.likes)
        assertEquals( "https://dog_pic.html" , fullPost.link)
        assertEquals(listOf("dog", "white", "pet") , fullPost.tags)
        assertEquals("23 août 2022 23:23:23" , fullPost.publishDate)
        assertEquals("32" , fullPost.ownerId)
        assertEquals("Super Star" , fullPost.ownerName)
        assertEquals("https://nice_face.com/pic.jpg" , fullPost.ownerPicture)
    }
}
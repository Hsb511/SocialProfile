package com.team23.home

import com.team23.api.models.PostPreview
import com.team23.api.models.UserPreview
import com.team23.home.data.mappers.toModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import kotlin.coroutines.ContinuationInterceptor

@ExperimentalCoroutinesApi
class PostMapperExtensions {
    private val testDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule(testDispatcher)

    @Test
    fun `given empty PostPreview, when toModel is called, then `() {
        // GIVEN
        val postPreview = PostPreview(
            id = "",
            text = "",
            image = "",
            likes = -1,
            tags = emptyList(),
            publishDate = "",
            owner = UserPreview(
                id = "",
                title = "",
                firstName = "",
                lastName = "",
                picture = ""
            )
        )
    }

    @Test
    fun `given valid PostPreview, when toModel is called, then returns correct PostModel`() {
        // GIVEN
        val postPreview = PostPreview(
            id = "23",
            text = "WOW",
            image = "https://img.dummyapi.io/photo-1564694202779-bc908c327862.jpg",
            likes = 23,
            tags = listOf("test", "wow"),
            publishDate = "2020-05-24T14:53:17.598Z",
            owner = UserPreview(
                id = "7",
                title = "Mr",
                firstName = "Super",
                lastName = "Man",
                picture = "https://randomuser.me/api/portraits/women/58.jpg"
            )
        )

        runBlocking {
            // WHEN
            val postModel = postPreview.toModel(testDispatcher)

            // THEN
            assertEquals("WOW", postModel.text)
            assertNotNull(postModel.image)
            assertEquals(23, postModel.likes)
            assertEquals(listOf("test", "wow"), postModel.tags)
            assertEquals("24 mai 2020 14:53:17", postModel.publishDate)
            assertEquals("7", postModel.ownerId)
            assertEquals("Super Man", postModel.ownerName)
            assertNotNull(postModel.ownerPicture)
        }
    }
}


@ExperimentalCoroutinesApi
class MainCoroutineRule(private val testDispatcher: TestDispatcher) : TestWatcher() {

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }

}
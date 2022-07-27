package com.team23.user

import com.team23.user.data.repositories.UserRepository
import com.team23.user.domain.models.ContactData
import com.team23.user.domain.models.ContactDataCategory
import com.team23.user.domain.models.UserModel
import com.team23.user.domain.usecases.GetUserUseCase
import com.team23.user.ui.viewobjects.UserVO
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.time.LocalDateTime

@RunWith(MockitoJUnitRunner::class)
class GetUserUseCaseTest {
    @Mock
    private lateinit var userRepository: UserRepository

    @Test
    fun `given empty userId, when getUserUseCase is called, then returns null`() {
        // GIVEN
        val userId = ""

        // WHEN
        runBlocking {
            Mockito.`when`(userRepository.getUserById(userId)).thenReturn(null)
            val userVO = GetUserUseCase(userRepository).execute(userId)

            // THEN
            assertNull(userVO)
        }
    }

    @Test
    fun `given valid userId & empty user, when getUserUseCase is called, then returns correct UserVO`() {
        // GIVEN
        val userId = "23"
        val userModel = UserModel(
            name = "",
            picture = null,
            gender = "",
            dateOfBirth = LocalDateTime.of(1996, 7, 6, 23, 23, 23),
            contactData = emptyList()
        )

        // WHEN
        runBlocking {
            Mockito.`when`(userRepository.getUserById(userId)).thenReturn(userModel)
            val userVO = GetUserUseCase(userRepository).execute(userId)

            // THEN
            assertEquals(UserVO(
                name = "",
                picture = null,
                backgroundResId = R.drawable.picture_beach,
                genderResId = R.drawable.ic_male,
                dateOfBirth = "06/07/1996",
                contactData = emptyList()
            ) , userVO)
        }
    }

    @Test
    fun `given valid userId & full user, when getUserUseCase is called, then returns correct UserVO`() {
        // GIVEN
        val userId = "23"
        val contactData = listOf(
            ContactData(ContactDataCategory.EMAIL, "jesse@alpineskihouse.com"),
            ContactData(ContactDataCategory.PHONE, "+1 6175550123"),
            ContactData(
                ContactDataCategory.ADDRESS,
                "9614, SÃ¸ndermarksvej, \r\nKongsvinger \r\nNordjylland \r\nDenmark"
            )
        )
        val userModel = UserModel(
            name = "Iron Man",
            picture = null,
            gender = "female",
            dateOfBirth = LocalDateTime.of(1936, 10, 5, 23, 23, 23),
            contactData = contactData
        )

        // WHEN
        runBlocking {
            Mockito.`when`(userRepository.getUserById(userId)).thenReturn(userModel)
            val userVO = GetUserUseCase(userRepository).execute(userId)

            // THEN
            assertEquals(UserVO(
                name = "Iron Man",
                picture = null,
                backgroundResId = R.drawable.picture_mountain,
                genderResId = R.drawable.ic_female,
                dateOfBirth = "05/10/1936",
                contactData = contactData
            ) , userVO)
        }
    }
}

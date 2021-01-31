package kr.co.fastcampus.eatgo.application

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import kr.co.fastcampus.eatgo.domain.User
import kr.co.fastcampus.eatgo.domain.UserRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ExtendWith(SpringExtension::class)
class UserServiceTest {

    private lateinit var sut: UserService

    @Mock
    private lateinit var userRepository: UserRepository

    @BeforeEach
    fun setup() {
        sut = UserService(userRepository)
    }

    @Test
    fun getUsers() {
        given(userRepository.findAll()).willReturn(
                listOf(
                        User(email = "test@example.net", name = "tester1", level = 1L),
                        User(email = "test2@example.net", name = "tester2", level = 3L )
                )
        )

        val users = sut.getUsers()

        val user = users[0]

        assertThat(user.email, `is`("test@example.net"))
    }

    @Test
    fun addUser() {
        val email = "test@example.com"
        val name = "tester"

        given(userRepository.save(any())).willReturn(User(email = email, name = name))

        val result = sut.addUser(email, name, 1L)

        assertThat(result.email, `is`(email))
        assertThat(result.name, `is`(name))
    }

    @Test
    internal fun updateUser() {
        val id = 1004L
        val email = "admin@exampl.com"
        val name = "Administrator"
        val newName = "Superman"
        val level = 100L
        val user = User(id, email, name, level)
        given(userRepository.findById(id)).willReturn(Optional.of(user))

        val result = sut.updateUser(id, email, newName, level)

        verify(userRepository).findById(id)
        assertThat(result.name, `is`(newName))
        assertThat(result.isAdmin, `is`(true))
    }
}
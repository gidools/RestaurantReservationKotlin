package kr.co.fastcampus.eatgo.application

import com.nhaarman.mockito_kotlin.*
import kr.co.fastcampus.eatgo.domain.User
import kr.co.fastcampus.eatgo.domain.UserRepository
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.lang.RuntimeException
import java.util.*
import kotlin.Throws

@ExtendWith(SpringExtension::class)
class UserServiceTest {

    @Mock
    private lateinit var userRepository: UserRepository

    private lateinit var sut: UserService

    @BeforeEach
    fun setup() {
        sut = UserService(userRepository)
    }

    @Test
    fun registerUser() {
        val email = "tester1@email.com"
        val name = "Tester1"
        val password = "test"
        given(userRepository.save(any())).willReturn(User(email = email, name = name, password = password))

        sut.registerUser(email, name, password)

        verify(userRepository).save(any())
    }

    @Test
    fun registerUserWithDuplicatedEmail() {
        val email = "tester1@email.com"
        val name = "Tester1"
        val password = "test"
        val mockUser = User(email = email, name = name, password = password)
        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser))

        val exception = Assertions.assertThrows(RuntimeException::class.java) {
            sut.registerUser(email, name, password)
        }

        assertThat(exception, `is`(instanceOf(DuplicateEmailException::class.java)))

        verify(userRepository, never()).save(any())
    }

}
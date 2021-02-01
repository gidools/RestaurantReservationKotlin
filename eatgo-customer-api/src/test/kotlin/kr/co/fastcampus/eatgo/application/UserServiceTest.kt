package kr.co.fastcampus.eatgo.application

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
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
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ExtendWith(SpringExtension::class)
class UserServiceTest {

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var passwordEncoder: PasswordEncoder

    private lateinit var sut: UserService

    @BeforeEach
    fun setup() {
        sut = UserService(userRepository, passwordEncoder)
    }

    @Test
    fun registerUser() {
        val email = "tester1@email.com"
        val name = "Tester1"
        val password = "test"
        given(userRepository.save(any())).willReturn(User(email = email, name = name, password = password))
        given(passwordEncoder.encode(any())).willReturn("testestest")

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

    @Test
    internal fun authenticateWithValidAttributes() {
        val email = "tester1@email.com"
        val password = "test"
        val name = "Tester1"
        val mockUser = User(email = email, name = name)
        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser))
        given(passwordEncoder.matches(any(), any())).willReturn(true)

        val result = sut.authenticate(email, password)

        assertThat(result.email, `is`(email))
    }

    @Test
    internal fun authenticateWithNotExistedEmail() {
        val email = "x@email.com"
        val password = "test"
        given(userRepository.findByEmail(email)).willReturn(Optional.empty())

        val exception = Assertions.assertThrows(RuntimeException::class.java) {
            sut.authenticate(email, password)
        }

        assertThat(exception, `is`(instanceOf(EmailNotExistedException::class.java)))
    }

    @Test
    internal fun authenticateWithWrongPassword() {
        val email = "tester1@email.com"
        val password = "x"
        val name = "Tester1"
        val mockUser = User(email = email, name = name)
        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser))

        Assertions.assertThrows(PasswordWrongException::class.java) {
            sut.authenticate(email, password)
        }
    }

}
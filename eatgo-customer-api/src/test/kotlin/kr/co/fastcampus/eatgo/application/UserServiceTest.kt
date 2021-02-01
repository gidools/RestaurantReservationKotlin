package kr.co.fastcampus.eatgo.application

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import kr.co.fastcampus.eatgo.domain.User
import kr.co.fastcampus.eatgo.domain.UserRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.springframework.test.context.junit.jupiter.SpringExtension

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

        val result = sut.registerUser(email, name, password)

        verify(userRepository).save(result)
    }

}
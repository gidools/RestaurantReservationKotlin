package kr.co.fastcampus.eatgo.interfaces

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import kr.co.fastcampus.eatgo.application.UserService
import kr.co.fastcampus.eatgo.domain.User
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(UserController::class)
class UserControllerTest {

    @MockBean
    private lateinit var userService: UserService

    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun create() {
        val email = "tester1@email.com"
        val name = "Tester1"
        val password = "test"
        val mockUser = User(id = 1004L, email = email, name = name, password = password)
        given(userService.registerUser(any(), any(), any())).willReturn(mockUser)

        mvc.perform(post(UserController.API_USERS)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"$email\", \"name\":\"$name\", \"password\":\"$password\"}"))
                .andExpect(status().isCreated)
                .andExpect(header().string("location", "/users/1004"))

        verify(userService).registerUser(email, name, password)
    }
}
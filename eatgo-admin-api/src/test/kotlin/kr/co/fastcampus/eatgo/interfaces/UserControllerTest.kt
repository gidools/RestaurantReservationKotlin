package kr.co.fastcampus.eatgo.interfaces

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import kr.co.fastcampus.eatgo.application.UserService
import kr.co.fastcampus.eatgo.domain.User
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(UserController::class)
class UserControllerTest {

    @MockBean
    private lateinit var userService: UserService

    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun list() {
        val users = listOf(
                User(email = "test@example.net", name = "tester1", level = 1L),
                User(email = "test2@example.net", name = "tester2", level = 3L )
        )

        given(userService.getUsers()).willReturn(users)

        mvc.perform(get(UserController.API_USERS))
                .andExpect(status().isOk)
                .andExpect(content().string(containsString("tester1")))
    }

    @Test
    fun addUser() {
        val email = "admin@exampl.com"
        val name = "Administrator"
        val level = 3L
        given(userService.addUser(email, name, level)).willReturn(User(1L, email, name, level))

        mvc.perform(post(UserController.API_USERS)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"$email\", \"name\":\"$name\",\"level\":$level}"))
                .andExpect(status().isCreated)

        verify(userService).addUser(email, name, level)
    }

    @Test
    fun updateUser() {
        val id = 1004L
        val email = "admin@exampl.com"
        val name = "Administrator"
        val level = 100L
        val user = User(id, email, name, level)
        given(userService.updateUser(id, email, name, level)).willReturn(user)

        mvc.perform(patch(UserController.API_USERS + "/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"$email\", \"name\":\"$name\",\"level\":$level}"))
                .andExpect(status().isOk)

        verify(userService).updateUser(id, email, name, level)
    }

}
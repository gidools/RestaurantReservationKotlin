package kr.co.fastcampus.eatgo.interfaces

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import kr.co.fastcampus.eatgo.application.EmailNotExistedException
import kr.co.fastcampus.eatgo.application.PasswordWrongException
import kr.co.fastcampus.eatgo.application.UserService
import kr.co.fastcampus.eatgo.domain.User
import kr.co.fastcampus.eatgo.utils.JwtUtil
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@ExtendWith(SpringExtension::class)
@WebMvcTest(SessionController::class)
class SessionControllerTest {

    @MockBean
    private lateinit var userService: UserService

    @MockBean
    private lateinit var jwtUtil: JwtUtil

    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun createWithValidAttributes() {
        val id = 1004L
        val email = "tester1@email.com"
        val password = "test"
        val name = "Tester1"
        val encodedPwd = "password"
        val mockUser = User(id = id, email = email, name = name, password = encodedPwd)
        given(userService.authenticate(email, password)).willReturn(mockUser)
        given(jwtUtil.createToken(id, name, mockUser.restaurantId)).willReturn("header.payload.signature")

        mvc.perform(
                post(SessionController.API_SESSION)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"$email\", \"password\":\"$password\"}"))
                .andExpect(status().isCreated)
                .andExpect(header().string("location", "/session"))
                .andExpect(content().string("{\"accessToken\":\"header.payload.signature\"}"))

        verify(userService).authenticate(email, password)
        verify(jwtUtil).createToken(id, name, mockUser.restaurantId)
    }

    @Test
    fun createWithRestaurantOwner() {
        val id = 1004L
        val email = "tester1@email.com"
        val password = "test"
        val name = "Tester1"
        val encodedPwd = "password"
        val mockUser = User(id = id, email = email, name = name, password = encodedPwd)
        mockUser.restaurantId = 369

        given(userService.authenticate(email, password)).willReturn(mockUser)
        given(jwtUtil.createToken(id, name, mockUser.restaurantId)).willReturn("header.payload.signature")

        mvc.perform(
            post(SessionController.API_SESSION)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"$email\", \"password\":\"$password\"}"))
            .andExpect(status().isCreated)
            .andExpect(header().string("location", "/session"))
            .andExpect(content().string("{\"accessToken\":\"header.payload.signature\"}"))

        verify(userService).authenticate(email, password)
        verify(jwtUtil).createToken(id, name, mockUser.restaurantId)
    }

    @Test
    fun createWithWrongPassword() {
        val email = "tester1@email.com"
        val password = "x"

        given(userService.authenticate(any(), any())).willThrow(PasswordWrongException())

        mvc.perform(
                post(SessionController.API_SESSION)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"$email\", \"password\":\"$password\"}"))
                .andExpect(status().isBadRequest)

        verify(userService).authenticate(email, password)
    }

    @Test
    fun createWithNotExistedEmail() {
        val email = "x@email.com"
        val password = "test"

        given(userService.authenticate(any(), any())).willThrow(EmailNotExistedException(email))

        mvc.perform(
                post(SessionController.API_SESSION)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"$email\", \"password\":\"$password\"}"))
                .andExpect(status().isBadRequest)

        verify(userService).authenticate(email, password)
    }

}
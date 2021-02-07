package kr.co.fastcampus.eatgo.interfaces

import com.nhaarman.mockito_kotlin.verify
import kr.co.fastcampus.eatgo.application.ReservationService
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*

@ExtendWith(SpringExtension::class)
@WebMvcTest(ReservationController::class)
class ReservationControllerTest {

    private val token =
        "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjIwMjAsIm5hbWUiOiJPd25lciIsInJlc3RhdXJhbnRJZCI6MTAwNH0.a5n4PWJ2-3yVyMaLGG0HSPXtH_mgpOvofpQ1OFkgDOQ"

    @MockBean
    private lateinit var reservationService: ReservationService

    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun list() {
        mvc.perform(get("/reservations")
            .header("Authorization", "Bearer $token"))
            .andExpect(status().isOk)

        verify(reservationService).getReservations(1004L)
    }

}
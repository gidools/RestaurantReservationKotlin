package kr.co.fastcampus.eatgo.interfaces

import com.nhaarman.mockito_kotlin.verify
import kr.co.fastcampus.eatgo.application.ReservationService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(ReservationController::class)
class ReservationControllerTest {

    private val token =
        "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJKYWNrIn0.8XJ_1fcyE2zpZI162crvilIlaNsRhnQPc90mKok0k48"

    @MockBean
    private lateinit var reservationService: ReservationService

    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun create() {
        val restaurantId = 200L
        val userId = 1004L
        val name = "Jack"
        val date = "2021-12-24"
        val time = "20:00"
        val partySize = 20

        mvc.perform(post("/restaurants/$restaurantId/reservations")
            .header("Authorization", "Bearer $token")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"date\":\"$date\",\"time\":\"$time\"," +
                    "\"partySize\":$partySize}"))
            .andExpect(status().isCreated)

        verify(reservationService).addReservation(restaurantId, userId, name, date, time, partySize)
    }

}
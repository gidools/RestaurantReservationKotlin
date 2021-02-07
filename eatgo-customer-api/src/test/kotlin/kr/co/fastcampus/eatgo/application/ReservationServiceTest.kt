package kr.co.fastcampus.eatgo.application

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import kr.co.fastcampus.eatgo.domain.Reservation
import kr.co.fastcampus.eatgo.domain.ReservationRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class ReservationServiceTest {

    @Mock
    private lateinit var reservationRepository: ReservationRepository

    private lateinit var sut: ReservationService

    @BeforeEach
    fun setup() {
        sut = ReservationService(reservationRepository)
    }

    @Test
    fun addReservation() {
        val restaurantId = 200L
        val userId = 1004L
        val name = "Jack"
        val date = "2021-12-24"
        val time = "20:00"
        val partySize = 20

        val mockReservation = Reservation().also {
            it.restaurantId = restaurantId
            it.userId = userId
            it.name = name
            it.date = date
            it.time = time
            it.partySize = partySize
        }

        given(reservationRepository.save(any())).will { invocation ->
            val reservation = invocation.getArgument(0) as Reservation
            reservation
        }

        val result = sut.addReservation(restaurantId, userId, name, date, time, partySize)

        assertThat(result.name, `is`(mockReservation.name))
        assertThat(result.date, `is`(mockReservation.date))
        assertThat(result.time, `is`(mockReservation.time))

        verify(reservationRepository).save(mockReservation)
    }
}
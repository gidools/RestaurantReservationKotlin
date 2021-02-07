package kr.co.fastcampus.eatgo.application

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import kr.co.fastcampus.eatgo.domain.ReservationRepository
import org.junit.jupiter.api.Assertions
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
    fun getReservations() {
        val restaurantId = 1004L

        sut.getReservations(restaurantId)

        verify(reservationRepository).findAllByRestaurantId(restaurantId)
    }

}
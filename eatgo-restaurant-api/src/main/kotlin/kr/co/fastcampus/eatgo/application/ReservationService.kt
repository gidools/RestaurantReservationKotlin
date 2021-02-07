package kr.co.fastcampus.eatgo.application

import kr.co.fastcampus.eatgo.domain.Reservation
import kr.co.fastcampus.eatgo.domain.ReservationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class ReservationService(@Autowired val reservationRepository: ReservationRepository) {

    fun getReservations(restaurantId: Long): List<Reservation> {
        return reservationRepository.findAllByRestaurantId(restaurantId)
    }

}

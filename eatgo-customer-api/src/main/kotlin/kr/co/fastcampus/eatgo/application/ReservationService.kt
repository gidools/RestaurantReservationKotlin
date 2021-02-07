package kr.co.fastcampus.eatgo.application

import kr.co.fastcampus.eatgo.domain.Reservation
import kr.co.fastcampus.eatgo.domain.ReservationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class ReservationService(@Autowired val reservationRepository: ReservationRepository) {

    fun addReservation(
        restaurantId: Long,
        userId: Long, name: String,
        date: String, time: String,
        partySize: Int
    ): Reservation {
        val reservation =  Reservation().also {
            it.restaurantId = restaurantId
            it.userId = userId
            it.name = name
            it.date = date
            it.time = time
            it.partySize = partySize
        }

        return reservationRepository.save(reservation)
    }

}

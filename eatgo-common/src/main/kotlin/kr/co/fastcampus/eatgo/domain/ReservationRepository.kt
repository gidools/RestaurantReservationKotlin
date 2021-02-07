package kr.co.fastcampus.eatgo.domain

import org.springframework.data.repository.CrudRepository

interface ReservationRepository: CrudRepository<Reservation, Long> {

    fun save(reservation: Reservation): Reservation
}

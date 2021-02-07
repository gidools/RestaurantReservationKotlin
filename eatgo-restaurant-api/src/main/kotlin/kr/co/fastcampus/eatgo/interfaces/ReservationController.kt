package kr.co.fastcampus.eatgo.interfaces

import io.jsonwebtoken.Claims
import kr.co.fastcampus.eatgo.application.ReservationService
import kr.co.fastcampus.eatgo.domain.Reservation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ReservationController {

    @Autowired
    private lateinit var reservationService: ReservationService

    @GetMapping("/reservations")
    fun list(authentication: Authentication): List<Reservation> {
        val claims: Claims = authentication.principal as Claims
        val restaurantId = claims["restaurantId"] as Int
        return reservationService.getReservations(restaurantId.toLong())
    }
}

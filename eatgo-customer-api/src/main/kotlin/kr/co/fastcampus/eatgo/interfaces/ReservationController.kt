package kr.co.fastcampus.eatgo.interfaces

import io.jsonwebtoken.Claims
import kr.co.fastcampus.eatgo.application.ReservationService
import kr.co.fastcampus.eatgo.domain.Reservation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class ReservationController {

    @Autowired
    private lateinit var reservationService: ReservationService

    @PostMapping("/restaurants/{restaurantId}/reservations")
    fun create(authentication: Authentication,
               @PathVariable("restaurantId") restaurantId: Long,
               @RequestBody resource: Reservation): ResponseEntity<Any> {
        val claims: Claims = authentication.principal as Claims
        val userId = claims["userId"] as Int
        val name = claims["name"] as String
        val date = resource.date
        val time = resource.time
        val partySize = resource.partySize

        val result = reservationService.addReservation(restaurantId, userId.toLong(), name, date, time, partySize)

        val url = "/restaurants/$restaurantId/reservations/${result.id}"
        return ResponseEntity.created(URI(url)).body("{}")
    }

}
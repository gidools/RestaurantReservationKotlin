package kr.co.fastcampus.eatgo.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Reservation(
    @Id
    @GeneratedValue
    val id: Long? = null) {

    var restaurantId: Long = 0L
    var userId = 0L
    var name = ""
    var date = ""
    var time = ""
    var partySize = 0
}
package kr.co.fastcampus.eatgo.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
data class Review(
        @Id
        @GeneratedValue
        var id: Long? = null,

        @field: NotEmpty
        var name: String = "",

        @field: NotNull
        var score: Int? = null,

        @field: NotEmpty
        var description: String = ""
) {
        var restaurantId: Long? = null
}

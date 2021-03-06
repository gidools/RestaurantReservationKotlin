package kr.co.fastcampus.eatgo.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotEmpty

@Entity
data class User(
    @Id
    @GeneratedValue
    var id: Long? = null,

    @NotEmpty
    var email: String,

    @NotEmpty
    var name: String,

    var level: Long = 1L,
    var password: String = ""
) {

    var restaurantId: Long? = null
        set(value) {
            field = value
            this.level = 50L
        }

}

val User.isAdmin get() = (level >= 3)
val User.isActive get() = (level > 0)
val User.isRestaurantOwner get() = (level == 50L)

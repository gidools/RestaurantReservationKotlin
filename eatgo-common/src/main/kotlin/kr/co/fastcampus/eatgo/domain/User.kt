package kr.co.fastcampus.eatgo.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotEmpty

@Entity
class User(
        @Id
        @GeneratedValue
        var id: Long? = null,

        @NotEmpty
        var email: String,

        @NotEmpty
        var name: String,

        var level: Long = 1L) {

    fun isAdmin() = (level >= 3)
    fun isActive() = (level > 0)
}

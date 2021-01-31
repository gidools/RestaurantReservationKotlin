package kr.co.fastcampus.eatgo.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
class User(
        @Id
        @GeneratedValue
        var id: Long? = null,

        @NotEmpty
        var email: String,

        @NotEmpty
        var name: String,

        @NotNull
        var level: Long = 1L) {

        val isAdmin: Boolean = (level >= 3)
}
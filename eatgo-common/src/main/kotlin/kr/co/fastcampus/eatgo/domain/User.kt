package kr.co.fastcampus.eatgo.domain

import com.sun.org.apache.xpath.internal.operations.Bool
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
        var password: String = "")

val User.isAdmin get() = (level >= 3)
val User.isActive get() = (level > 0)

package kr.co.fastcampus.eatgo.domain

import com.fasterxml.jackson.annotation.JsonInclude
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class MenuItem(
        @Id
        @GeneratedValue
        val id: Long? = null,
        val name: String = "",
        @Transient
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        var destroy: Boolean = false) {

    var restaurantId: Long? = null

}
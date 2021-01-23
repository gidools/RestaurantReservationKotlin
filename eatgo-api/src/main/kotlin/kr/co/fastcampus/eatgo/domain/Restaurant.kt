package kr.co.fastcampus.eatgo.domain

import com.fasterxml.jackson.annotation.JsonInclude
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotEmpty

@Entity
data class Restaurant(
        @Id
        @GeneratedValue
        var id: Long? = null,

        @field: NotEmpty
        var name: String,

        @field: NotEmpty
        var address: String) {

    val information get() = "$name in $address"

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var menuItems: List<MenuItem> = arrayListOf()

}
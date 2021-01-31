package kr.co.fastcampus.eatgo.domain

import com.fasterxml.jackson.annotation.JsonInclude
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
data class Restaurant(
        @Id
        @GeneratedValue
        var id: Long? = null,

        @field: NotEmpty
        var name: String,

        @field: NotEmpty
        var address: String,

        var categoryId: Long? = null) {

    val information get() = "$name in $address"

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var menuItems: List<MenuItem> = arrayListOf()

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var reviews: List<Review> = arrayListOf()

}
package kr.co.fastcampus.eatgo.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Restaurant(
        @Id
        @GeneratedValue
        var id: Long? = null,
        var name: String,
        var address: String) {

    val information get() = "$name in $address"

    @Transient
    var menuItems: List<MenuItem> = arrayListOf()

}
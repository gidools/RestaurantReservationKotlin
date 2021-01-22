package kr.co.fastcampus.eatgo.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Restaurant(
        @Id
        @GeneratedValue
        val id: Long? = null,
        val name: String,
        val address: String) {

    val information = "$name in $address"

    @Transient
    val menuItems = arrayListOf<MenuItem>()

    fun setMenuItems(menuItems: List<MenuItem>) {
        this.menuItems.clear()
        this.menuItems.addAll(menuItems)
    }

}
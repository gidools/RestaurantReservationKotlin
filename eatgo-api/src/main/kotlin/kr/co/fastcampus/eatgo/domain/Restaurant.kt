package kr.co.fastcampus.eatgo.domain

data class Restaurant(var id: Long, val name: String, val address: String) {

    constructor(name: String, address: String) : this(0, name, address)

    val information = "$name in $address"

    val menuItems = arrayListOf<MenuItem>()

    fun setMenuItems(menuItems: List<MenuItem>) {
        this.menuItems.clear()
        this.menuItems.addAll(menuItems)
    }

}
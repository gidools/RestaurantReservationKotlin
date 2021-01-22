package kr.co.fastcampus.eatgo.domain

data class Restaurant(val id: Long, val name: String, val address: String) {
    val information = "$name in $address"
}
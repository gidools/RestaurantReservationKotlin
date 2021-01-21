package kr.co.fastcampus.eatgo.domain

data class Restaurant(val name: String, val address: String) {
    val information = "$name in $address"
}
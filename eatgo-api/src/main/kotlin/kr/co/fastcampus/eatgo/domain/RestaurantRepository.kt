package kr.co.fastcampus.eatgo.domain

interface RestaurantRepository {
    fun findAll(): List<Restaurant>
    fun findById(id: Long): Restaurant
}
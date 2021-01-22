package kr.co.fastcampus.eatgo.domain

import org.springframework.stereotype.Component

@Component
class RestaurantRepository {

    private val restaurants = arrayListOf<Restaurant>()

    init {
        restaurants.add(Restaurant(1004L, "Bob zip", "Seoul"))
        restaurants.add(Restaurant(2020L, "Cyber food", "Seoul"))
    }

    fun findAll(): List<Restaurant> {
        return restaurants
    }

    fun findById(id: Long): Restaurant {
        return restaurants.first { it.id == id }
    }

}
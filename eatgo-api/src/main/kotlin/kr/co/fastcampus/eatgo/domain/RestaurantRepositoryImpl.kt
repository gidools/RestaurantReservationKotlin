package kr.co.fastcampus.eatgo.domain

import org.springframework.stereotype.Component

@Component
class RestaurantRepositoryImpl : RestaurantRepository {

    private val restaurants = arrayListOf<Restaurant>()

    init {
        restaurants.add(Restaurant(1004L, "Bob zip", "Seoul"))
        restaurants.add(Restaurant(2020L, "Cyber food", "Seoul"))
    }

    override fun findAll(): List<Restaurant> {
        return restaurants
    }

    override fun findById(id: Long): Restaurant {
        return restaurants.first { it.id == id }
    }

    override fun save(restaurant: Restaurant): Restaurant {
        restaurant.id = 1234L
        restaurants.add(restaurant)
        return restaurant
    }

}
package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.domain.Restaurant
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RestaurantController {

    @GetMapping(GET_RESTAURANTS)
    fun list(): List<Restaurant> {
        val restaurants = arrayListOf<Restaurant>()
        restaurants.add(Restaurant(1004, "Bob zip", "Seoul"))
        return restaurants
    }

    companion object {
        const val GET_RESTAURANTS = "/restaurants"
    }

}
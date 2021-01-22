package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.domain.Restaurant
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class RestaurantController {

    @GetMapping(GET_RESTAURANTS)
    fun list(): List<Restaurant> {
        val restaurants = arrayListOf<Restaurant>()
        restaurants.add(Restaurant(1004, "Bob zip", "Seoul"))
        return restaurants
    }

    @GetMapping("$GET_RESTAURANTS/{id}")
    fun detail(@PathVariable("id") id: Long): Restaurant? {
        val restaurants = arrayListOf<Restaurant>()
        restaurants.add(Restaurant(1004L, "Bob zip", "Seoul"))
        restaurants.add(Restaurant(2020L, "Cyber food", "Seoul"))

        return restaurants.first { it.id == id }
    }

    companion object {
        const val GET_RESTAURANTS = "/restaurants"
    }

}
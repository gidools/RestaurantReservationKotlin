package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.RestaurantService
import kr.co.fastcampus.eatgo.domain.Restaurant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class RestaurantController {

    @Autowired
    private lateinit var restaurantService: RestaurantService

    @GetMapping(GET_RESTAURANTS)
    fun list(): List<Restaurant> {
        return restaurantService.getRestaurants()
    }

    @GetMapping("$GET_RESTAURANTS/{id}")
    fun detail(@PathVariable("id") id: Long): Restaurant {
        return restaurantService.getRestaurant(id)
    }

    companion object {
        const val GET_RESTAURANTS = "/restaurants"
    }

}
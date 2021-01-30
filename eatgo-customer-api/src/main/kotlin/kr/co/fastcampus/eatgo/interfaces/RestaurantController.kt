package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.RestaurantService
import kr.co.fastcampus.eatgo.domain.Restaurant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class RestaurantController {

    @Autowired
    private lateinit var restaurantService: RestaurantService

    @GetMapping(API_RESTAURANTS)
    fun list(@RequestParam("region") region: String): List<Restaurant> {
        return restaurantService.getRestaurants(region)
    }

    @GetMapping("$API_RESTAURANTS/{id}")
    fun detail(@PathVariable("id") id: Long): Restaurant {
        return restaurantService.getRestaurant(id)
    }

    companion object {
        const val API_RESTAURANTS = "/restaurants"
    }

}
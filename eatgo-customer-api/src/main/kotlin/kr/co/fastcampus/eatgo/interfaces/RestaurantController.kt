package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.RestaurantService
import kr.co.fastcampus.eatgo.domain.Restaurant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

@RestController
class RestaurantController {

    @Autowired
    private lateinit var restaurantService: RestaurantService

    @GetMapping(API_RESTAURANTS)
    fun list(): List<Restaurant> {
        return restaurantService.getRestaurants()
    }

    @GetMapping("$API_RESTAURANTS/{id}")
    fun detail(@PathVariable("id") id: Long): Restaurant {
        return restaurantService.getRestaurant(id)
    }

    companion object {
        const val API_RESTAURANTS = "/restaurants"
    }

}
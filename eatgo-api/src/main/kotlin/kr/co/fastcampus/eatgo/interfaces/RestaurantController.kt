package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.RestaurantService
import kr.co.fastcampus.eatgo.domain.Restaurant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

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

    @PostMapping(API_RESTAURANTS)
    fun create(@RequestBody resource: Restaurant): ResponseEntity<Any?> {
        val name = resource.name
        val address = resource.address

        val restaurant = Restaurant(1234L, name, address)
        val created = restaurantService.addRestaurant(restaurant)

        val location = URI("/restaurants/${restaurant.id}")
        return ResponseEntity.created(location).body("{}")
    }

    companion object {
        const val API_RESTAURANTS = "/restaurants"
    }

}
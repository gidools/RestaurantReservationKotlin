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

    @PostMapping(API_RESTAURANTS)
    fun create(@Valid @RequestBody resource: Restaurant): ResponseEntity<Any?> {
        val name = resource.name
        val address = resource.address

        val restaurant = Restaurant(name = name, address = address)
        val created = restaurantService.addRestaurant(restaurant)

        val location = URI("/restaurants/${restaurant.id}")
        return ResponseEntity.created(location).body("{}")
    }

    @PatchMapping("${API_RESTAURANTS}/{id}")
    fun update(@PathVariable("id") id: Long, @Valid @RequestBody resource: Restaurant): String {
        restaurantService.updateRestaurant(id, resource.name, resource.address)
        return "{}"
    }

    companion object {
        const val API_RESTAURANTS = "/restaurants"
    }

}
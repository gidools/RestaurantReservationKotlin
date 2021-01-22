package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.domain.MenuItemRepository
import kr.co.fastcampus.eatgo.domain.Restaurant
import kr.co.fastcampus.eatgo.domain.RestaurantRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class RestaurantController {

    @Autowired
    private lateinit var menuItemRepository: MenuItemRepository

    @Autowired
    private lateinit var restaurantRepository: RestaurantRepository

    @GetMapping(GET_RESTAURANTS)
    fun list(): List<Restaurant> {
        return restaurantRepository.findAll()
    }

    @GetMapping("$GET_RESTAURANTS/{id}")
    fun detail(@PathVariable("id") id: Long): Restaurant? {
        val restaurant = restaurantRepository.findById(id)
        val menuItems = menuItemRepository.findAllByRestaurantId(id)
        restaurant.setMenuItems(menuItems)
        return restaurantRepository.findById(id)
    }

    companion object {
        const val GET_RESTAURANTS = "/restaurants"
    }

}
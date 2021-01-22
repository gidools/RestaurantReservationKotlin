package kr.co.fastcampus.eatgo.application

import kr.co.fastcampus.eatgo.domain.MenuItemRepository
import kr.co.fastcampus.eatgo.domain.Restaurant
import kr.co.fastcampus.eatgo.domain.RestaurantRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RestaurantService(@Autowired
                        private var restaurantRepository: RestaurantRepository,
                        @Autowired
                        val menuItemRepository: MenuItemRepository) {

    fun getRestaurant(id: Long): Restaurant {
        val menuItems = menuItemRepository.findAllByRestaurantId(id)
        val restaurant = restaurantRepository.findById(id)
        restaurant.setMenuItems(menuItems)
        return restaurant
    }

    fun getRestaurants(): List<Restaurant> {
        return restaurantRepository.findAll()
    }

    fun addRestaurant(restaurant: Restaurant): Restaurant? {
        return null
    }

}
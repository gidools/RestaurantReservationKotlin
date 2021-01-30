package kr.co.fastcampus.eatgo.application

import kr.co.fastcampus.eatgo.domain.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RestaurantService(@Autowired
                        private var restaurantRepository: RestaurantRepository,
                        @Autowired
                        val menuItemRepository: MenuItemRepository,
                        @Autowired
                        val reviewRepository: ReviewRepository) {

    fun getRestaurant(id: Long): Restaurant {
        val restaurant = restaurantRepository.findById(id).orElseThrow { RestaurantNotFoundException(id) }
        val menuItems = menuItemRepository.findAllByRestaurantId(id)
        val reviews = reviewRepository.findAllByRestaurantId(id)
        restaurant.menuItems = menuItems
        restaurant.reviews = reviews
        return restaurant
    }

    fun getRestaurants(region: String): List<Restaurant> {
        return restaurantRepository.findByAddressContaining(region)
    }

}
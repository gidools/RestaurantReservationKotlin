package kr.co.fastcampus.eatgo.application

import kr.co.fastcampus.eatgo.domain.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

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

    fun getRestaurants(): List<Restaurant> {
        return restaurantRepository.findAll()
    }

    fun addRestaurant(restaurant: Restaurant): Restaurant {
        return restaurantRepository.save(restaurant)
    }

    @Transactional
    fun updateRestaurant(id: Long, name: String, address: String): Restaurant {
        val restaurant = restaurantRepository.findById(id).orElse(null)
        restaurant.name = name;
        restaurant.address = address
        return restaurant
    }

}
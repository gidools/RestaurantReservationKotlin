package kr.co.fastcampus.eatgo.application

import kr.co.fastcampus.eatgo.domain.MenuItemRepository
import kr.co.fastcampus.eatgo.domain.Restaurant
import kr.co.fastcampus.eatgo.domain.RestaurantNotFoundException
import kr.co.fastcampus.eatgo.domain.RestaurantRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class RestaurantService(@Autowired
                        private var restaurantRepository: RestaurantRepository,
                        @Autowired
                        val menuItemRepository: MenuItemRepository) {

    fun getRestaurant(id: Long): Restaurant {
        val restaurant = restaurantRepository.findById(id).orElseThrow { RestaurantNotFoundException(id)  }
        val menuItems = menuItemRepository.findAllByRestaurantId(id)
        restaurant.menuItems = menuItems
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
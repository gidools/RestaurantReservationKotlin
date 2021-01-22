package kr.co.fastcampus.eatgo.application

import kr.co.fastcampus.eatgo.domain.MenuItemRepository
import kr.co.fastcampus.eatgo.domain.MenuItemRepositoryImpl
import kr.co.fastcampus.eatgo.domain.RestaurantRepository
import kr.co.fastcampus.eatgo.domain.RestaurantRepositoryImpl
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class RestaurantServiceTest {

    private lateinit var restaurantService: RestaurantService
    private lateinit var restaurantRepository: RestaurantRepository
    private lateinit var menuItemRepository: MenuItemRepository

    @BeforeEach
    fun setup() {
        restaurantRepository = RestaurantRepositoryImpl()
        menuItemRepository = MenuItemRepositoryImpl()
        restaurantService = RestaurantService(restaurantRepository, menuItemRepository)
    }

    @Test
    fun getRestaurant() {
        val result = restaurantService.getRestaurant(1004L)

        assertThat(result.id, `is`(1004L))

        val menuItem = result.menuItems[0]

        assertThat(menuItem.name, `is`("Kimchi"))
    }

    @Test
    internal fun getRestaurants() {
        val result = restaurantService.getRestaurants()

        val restaurant = result[0]
        assertThat(restaurant.id, `is`(1004L))
    }
}
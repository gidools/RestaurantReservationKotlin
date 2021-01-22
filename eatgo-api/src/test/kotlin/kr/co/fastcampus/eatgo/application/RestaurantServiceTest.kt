package kr.co.fastcampus.eatgo.application

import kr.co.fastcampus.eatgo.domain.MenuItem
import kr.co.fastcampus.eatgo.domain.MenuItemRepository
import kr.co.fastcampus.eatgo.domain.Restaurant
import kr.co.fastcampus.eatgo.domain.RestaurantRepository
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class RestaurantServiceTest {

    private lateinit var restaurantService: RestaurantService

    @Mock
    private lateinit var restaurantRepository: RestaurantRepository

    @Mock
    private lateinit var menuItemRepository: MenuItemRepository

    @BeforeEach
    fun setup() {
        restaurantService = RestaurantService(restaurantRepository, menuItemRepository)
        makeRestaurantRepository()
        makeMenuItemRepository()
    }

    @Test
    fun getRestaurant() {
        val result = restaurantService.getRestaurant(1004L)

        assertThat(result.id, `is`(1004L))

        val menuItem = result.menuItems[0]

        assertThat(menuItem.name, `is`("Kimchi"))
    }

    @Test
    fun getRestaurants() {
        val result = restaurantService.getRestaurants()

        val restaurant = result[0]
        assertThat(restaurant.id, `is`(1004L))
    }

    private fun getTestRestaurants(): List<Restaurant> {
        return listOf(
                Restaurant(1004, "Bob zip", "Seoul"),
                Restaurant(2020, "Cyber food", "Seoul"),
                Restaurant(2021, "Joker house", "Busan")
        )
    }

    private fun getRestaurant(id: Long): Restaurant {
        val restaurants = getTestRestaurants()
        return restaurants.first { it.id == id }
    }

    private fun makeRestaurantRepository() {
        given(restaurantRepository.findAll()).willReturn(getTestRestaurants())

        val restaurant1 = getRestaurant(1004L)
        given(restaurantRepository.findById(1004L)).willReturn(restaurant1)

        val restaurant2 = getRestaurant(2020)
        given(restaurantRepository.findById(2020)).willReturn(restaurant2)
    }

    private fun makeMenuItemRepository() {
        val menuItems = listOf(MenuItem("Kimchi"))
        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems)
    }

}
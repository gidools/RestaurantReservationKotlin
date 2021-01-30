package kr.co.fastcampus.eatgo.application

import kr.co.fastcampus.eatgo.domain.*
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.*
import org.mockito.Mock
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ExtendWith(SpringExtension::class)
class RestaurantServiceTest {

    private lateinit var sut: RestaurantService
    @Mock
    private lateinit var restaurantRepository: RestaurantRepository

    @Mock
    private lateinit var menuItemRepository: MenuItemRepository

    @Mock
    private lateinit var reviewRepository: ReviewRepository

    @BeforeEach
    fun setup() {
        sut = RestaurantService(restaurantRepository, menuItemRepository, reviewRepository)
        makeRestaurantRepository()
        makeMenuItemRepository()
        makeReviewRepository()
    }

    @Test
    fun getRestaurantWithExisted() {
        val result = sut.getRestaurant(1004L)

        assertThat(result.id, `is`(1004L))

        val menuItem = result.menuItems[0]

        assertThat(menuItem.name, `is`("Kimchi"))

        val review = result.reviews[0]
        assertThat(review.description, `is`("Good"))
    }

    @Test
    fun getRestaurantWithNotExisted() {
        assertThrows(RestaurantNotFoundException::class.java) {
            sut.getRestaurant(404L)
        }
    }

    @Test
    fun getRestaurants() {
        val result = sut.getRestaurants(region = "Seoul")

        val restaurant = result[0]
        assertThat(restaurant.address, `is`("Seoul"))
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
        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant1))

        val restaurant2 = getRestaurant(2020)
        given(restaurantRepository.findById(2020)).willReturn(Optional.of(restaurant2))

        given(restaurantRepository.findByAddressContaining("Seoul")).willReturn(listOf(
                Restaurant(1004, "Bob zip", "Seoul"),
                Restaurant(2020, "Cyber food", "Seoul")))
    }

    private fun makeMenuItemRepository() {
        val menuItems = listOf(MenuItem(name = "Kimchi"))
        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems)
    }

    private fun makeReviewRepository() {
        val reviews = listOf(
                Review(name = "Jack", score = 3, description = "Good"),
                Review(name = "James", score = 1, description = "Bad")
        )
        given(reviewRepository.findAllByRestaurantId(1004L)).willReturn(reviews)
    }

}
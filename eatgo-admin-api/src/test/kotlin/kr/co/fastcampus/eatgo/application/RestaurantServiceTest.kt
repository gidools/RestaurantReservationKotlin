package kr.co.fastcampus.eatgo.application

import kr.co.fastcampus.eatgo.domain.*
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ExtendWith(SpringExtension::class)
class RestaurantServiceTest {

    private lateinit var sut: RestaurantService
    @Mock
    private lateinit var restaurantRepository: RestaurantRepository

    @BeforeEach
    fun setup() {
        sut = RestaurantService(restaurantRepository)
        makeRestaurantRepository()
    }

    @Test
    fun getRestaurantWithExisted() {
        val result = sut.getRestaurant(1004L)

        assertThat(result.id, `is`(1004L))
    }

    @Test
    fun getRestaurantWithNotExisted() {
        assertThrows(RestaurantNotFoundException::class.java) {
            sut.getRestaurant(404L)
        }
    }

    @Test
    fun getRestaurants() {
        val result = sut.getRestaurants()

        val restaurant = result[0]
        assertThat(restaurant.id, `is`(1004L))
    }

    @Test
    fun addRestaurant() {
        val restaurant = Restaurant(name = "Bob zip", address =  "Seoul")
        val saved = Restaurant(1234L, "Bob zip", "Seoul")
        given(restaurantRepository.save(restaurant)).willReturn(saved)

        val created = sut.addRestaurant(restaurant)

        assertThat(created.id, `is`(1234L))
    }

    @Test
    fun updateRestaurant() {
        val restaurant = Restaurant(1004L, "Bob zip", "Seoul")
        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant))

        sut.updateRestaurant(restaurant.id!!, "Sool zip", "Busan")

        assertThat(restaurant.name, `is`("Sool zip"))
        assertThat(restaurant.address, `is`("Busan"))
    }

    private fun getTestRestaurants(): List<Restaurant> {
        return listOf(
                Restaurant(1004, "Bob zip", "Seoul", categoryId = 1L),
                Restaurant(2020, "Cyber food", "Seoul", categoryId = 2L),
                Restaurant(2021, "Joker house", "Busan", categoryId = 3L)
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
    }

}
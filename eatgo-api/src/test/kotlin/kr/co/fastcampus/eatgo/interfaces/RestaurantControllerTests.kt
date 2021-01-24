package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.RestaurantService
import kr.co.fastcampus.eatgo.domain.MenuItem
import kr.co.fastcampus.eatgo.domain.Restaurant
import kr.co.fastcampus.eatgo.domain.RestaurantNotFoundException
import kr.co.fastcampus.eatgo.domain.Review
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(RestaurantController::class)
class RestaurantControllerTests {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var restaurantService: RestaurantService

    @Test
    fun list() {
        val restaurants = getRestaurants()
        given(restaurantService.getRestaurants()).willReturn(restaurants)

        mvc.perform(get(RestaurantController.API_RESTAURANTS))
                .andExpect(status().isOk)
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Bob zip\"")
                ))
    }

    @Test
    fun detailWithExisted() {
        val restaurant = getRestaurant(1004L)
        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant)

        val review = Review(name = "Jack", score = 5, description = "great")
        restaurant.reviews = listOf(review)

        mvc.perform(get(RestaurantController.API_RESTAURANTS + "/1004"))
                .andExpect(status().isOk)
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Bob zip\"")
                ))
                .andExpect(content().string(
                        containsString("Kimchi")
                ))
                .andExpect(content().string(containsString("\"description\":\"great\"")))
    }

    @Test
    fun detailWithNotExisted() {
        given(restaurantService.getRestaurant(404)).willThrow(RestaurantNotFoundException(404L))

        mvc.perform(get(RestaurantController.API_RESTAURANTS + "/404"))
                .andExpect(status().isNotFound)
                .andExpect(content().string("{}"))
    }

    @Test
    fun createWithValidDaa() {
        val newRestaurant = Restaurant(
                name = "Beryong",
                address = "Seoul"
        )

        mvc.perform(post(RestaurantController.API_RESTAURANTS)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Beryong\",\"address\":\"Seoul\"}"))
                .andExpect(status().isCreated)
                .andExpect(content().string("{}"))


        verify(restaurantService).addRestaurant(newRestaurant)
    }

    @Test
    fun createWithInvalidData() {
        mvc.perform(post(RestaurantController.API_RESTAURANTS)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\",\"address\":\"\"}"))
                .andExpect(status().isBadRequest)
    }

    @Test
    internal fun update() {
        mvc.perform(patch(RestaurantController.API_RESTAURANTS + "/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Beryong\",\"address\":\"Busan\"}"))
                .andExpect(status().isOk)

        verify(restaurantService).updateRestaurant(1004L, "Beryong", "Busan")
    }

    @Test
    internal fun updateWithInvalidData() {
        mvc.perform(patch(RestaurantController.API_RESTAURANTS + "/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\",\"address\":\"\"}"))
                .andExpect(status().isBadRequest)
    }

    private fun getRestaurants(): List<Restaurant> {
        return listOf(
                Restaurant(1004, "Bob zip", "Seoul"),
                Restaurant(2020, "Cyber food", "Seoul"),
                Restaurant(2021, "Joker house", "Busan")
        )
    }

    private fun getRestaurant(id: Long): Restaurant {
        val restaurants = getRestaurants()
        val restaurant = restaurants.first { it.id == id }
        restaurant.menuItems = listOf(MenuItem(name = "Kimchi"))
        return restaurant
    }

}